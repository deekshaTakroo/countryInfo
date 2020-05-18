package com.example.countryinfo.utils.apis

import androidx.lifecycle.LiveData
import com.example.countryinfo.pojos.CountryListPojo
import retrofit2.Retrofit

interface APILiveDataInterface {
    fun getCountryList():LiveData<ArrayList<CountryListPojo>?>?
}