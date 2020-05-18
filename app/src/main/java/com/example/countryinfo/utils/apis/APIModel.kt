package com.example.countryinfo.utils.apis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.pojos.CountryListPojo
import com.example.countryinfo.utils.di.CountryInfoApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class APIModel : APILiveDataInterface {

    @Inject
    lateinit var retrofit: Retrofit
    init {
       CountryInfoApplicationClass.networkComponent.inject(this)
    }

    override fun getCountryList(): LiveData<ArrayList<CountryListPojo>?>? {
        val data = MutableLiveData<ArrayList<CountryListPojo>>()
        val apiInterface : APIInterface = retrofit.create(
            APIInterface::class.java)
        val responseBodyCall : Call<ArrayList<CountryListPojo>?>? = apiInterface?.getCountryList()
        responseBodyCall?.enqueue(object : Callback<ArrayList<CountryListPojo>?>
        {
            override fun onFailure(call: Call<ArrayList<CountryListPojo>?>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<CountryListPojo>?>,
                response: Response<ArrayList<CountryListPojo>?>
            ) {
                if (response.code()==200)
                    data.value=response.body()
            }

        })

        return data
    }
}