package com.example.countryinfo.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfo.R
import com.example.countryinfo.adapters.CountryListRecyclerViewAdapter
import com.example.countryinfo.interfaces.RecyclerViewItemClickListener
import com.example.countryinfo.pojos.CountryListPojo
import com.example.countryinfo.utils.apis.APIViewModel
import com.example.countryinfo.utils.database.AppDatabase
import com.example.countryinfo.utils.database.Converters
import com.example.countryinfo.utils.database.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs


class MainActivity : AppCompatActivity(), RecyclerViewItemClickListener {
    var countryAl = ArrayList<CountryListPojo>()
    lateinit var countryAl1: List<Task>
    lateinit var countryListRecyclerViewAdapter: CountryListRecyclerViewAdapter
    var countryNameAL = ArrayList<String>()
    var countryPopAL = ArrayList<String>()
    var countryFlagAL = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        countryListRv.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL,
            false
        )
        countryListRecyclerViewAdapter =
            CountryListRecyclerViewAdapter(
                countryNameAL,
                countryPopAL,
                countryFlagAL,
                this@MainActivity,
                this@MainActivity
            )
        countryListRv.adapter = countryListRecyclerViewAdapter

        if (isConnectingToInternet())
            countryListAPI()
        else {
            GlobalScope.launch {
                countryAl1 = AppDatabase.getDatabase(this@MainActivity).taskDao()?.fetchAllData()!!

                for (i in countryAl1.indices) {
                    countryNameAL.add(countryAl1[i].name.toString())
                    countryPopAL.add(countryAl1[i].population.toString())
                    countryFlagAL.add(countryAl1[i].flag.toString())
                }

                delay(1000)

            }
            countryListRecyclerViewAdapter.updateRecyclerView(
                countryNameAL,
                countryPopAL,
                countryFlagAL
            )

        }


        searchCountryNameEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                countryNameAL.clear()
                countryPopAL.clear()
                countryFlagAL.clear()
                for (i in countryAl.indices) {
                    if (countryAl[i].name?.toLowerCase()?.contains(s.toString().toLowerCase())!!) {
                        countryNameAL.add(countryAl[i].name.toString())
                        countryPopAL.add(convertNumber(countryAl[i].population!!).toString())
                        countryFlagAL.add(countryAl[i].flag.toString())
                    }
                }
                countryListRecyclerViewAdapter.updateRecyclerView(
                    countryNameAL,
                    countryPopAL,
                    countryFlagAL
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }


    private fun countryListAPI() {
        var apiViewModel = APIViewModel()
        apiViewModel.requestCountryListData()
        apiViewModel.getCountryListCallBack()
            ?.observe(this,
                Observer<ArrayList<CountryListPojo>> { t ->
                    if (t != null) {
                        countryAl = t

                        countryAl.sortBy {
                            it.name
                        }

                        for (i in countryAl.indices) {
                            countryNameAL.add(t[i].name.toString())
                            countryPopAL.add(convertNumber(t[i].population!!).toString())
                            countryFlagAL.add(t[i].flag.toString())

                        }
                        GlobalScope.launch {
                            for (i in t.indices) {
                                val task = Task()
                                task.name = countryNameAL[i]
                                task.population = countryPopAL[i]
                                task.currencyObject =
                                    Converters.stringFromCurrencyArrayList(t[i].currencies)
                                task.languageObject =
                                    Converters.stringFromLanguageArrayList(t[i].languages)
                                task.capital = t[i].capital
                                task.latlong = Converters.stringFromLatlongArrayList(t[i].latlng)
                                task.flag = countryFlagAL[i]

                                AppDatabase.getDatabase(this@MainActivity).taskDao()?.insert(task)
                            }

                        }

                        countryListRecyclerViewAdapter.updateRecyclerView(
                            countryNameAL,
                            countryPopAL,
                            countryFlagAL
                        )
                    }
                })
    }

    fun convertNumber(number: Int): String? {
        val thousands = 1000
        val million = 1000000
        val billion = 1000000000
        return when {
            number in thousands until million -> {
                ((abs(number) / 1000)).toString() + "K"
            }
            number in million until billion -> {
                ((abs(number) / 1000000)).toString() + "M"
            }
            number >= billion -> {
                ((abs(number) / 1000000000)).toString() + "B"
            }
            else -> number.toString()
        }
    }

    override fun onItemClicked(position: Int) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.putExtra("countryFlag", countryFlagAL[position])
        startActivity(intent)

    }

    private fun isConnectingToInternet(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        for (i in networkInfo.indices) {
            if (networkInfo[i].state == NetworkInfo.State.CONNECTED) return true
        }
        return false
    }


}
