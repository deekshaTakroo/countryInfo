package com.example.countryinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import com.ahmadrosid.svgloader.SvgLoader
import com.example.countryinfo.R
import com.example.countryinfo.databinding.ActivityCountryDetailBinding
import com.example.countryinfo.utils.database.AppDatabase
import com.example.countryinfo.utils.database.Converters
import com.example.countryinfo.utils.database.Task
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CountryDetailActivity : AppCompatActivity() {

    lateinit var countryDetailBinding: ActivityCountryDetailBinding
    lateinit var countryDetail : Task
    lateinit var currencyAl : ArrayList<String>
    lateinit var languageAl : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_country_detail)

        countryDetailBinding.backArrowClick = this

        val countryFlag = intent.extras?.get("countryFlag").toString()
        SvgLoader.pluck().with(this@CountryDetailActivity).load(countryFlag,countryFlagIv)                                        //this only works in main thread

        GlobalScope.launch {

            countryDetail = AppDatabase.getDatabase(this@CountryDetailActivity).taskDao()?.fetchParticularCountry(countryFlag)!!
            countryDetailBinding.countryName = countryDetail.name
            countryDetailBinding.countryPopulation = countryDetail.population
            currencyAl = Converters.currencyFromString(countryDetail.currencyObject)!!
            countryDetailBinding.countryCurrency = TextUtils.join(", ", currencyAl)
            languageAl = Converters.languageFromString(countryDetail.languageObject)!!
            countryDetailBinding.countryLanguages = TextUtils.join(", ", languageAl)
            countryDetailBinding.countryCapital = countryDetail.capital
            countryDetailBinding.countryLatLong = countryDetail.latlong

        }




    }
}
