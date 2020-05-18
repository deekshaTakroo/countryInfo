package com.example.countryinfo.utils.apis

import com.example.countryinfo.pojos.CountryListPojo
import com.example.countryinfo.utils.apis.APIConstants
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET(APIConstants.countryList)
    fun getCountryList(): Call<ArrayList<CountryListPojo>?>?
}