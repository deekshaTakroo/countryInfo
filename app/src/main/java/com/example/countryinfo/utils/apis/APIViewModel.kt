package com.example.countryinfo.utils.apis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.countryinfo.pojos.CountryListPojo

class APIViewModel {

    var apiModel = APIModel()

    lateinit var countryListMediatorLiveData: MediatorLiveData<ArrayList<CountryListPojo>>

    fun getCountryListCallBack(): LiveData<ArrayList<CountryListPojo>>? {
        return countryListMediatorLiveData
    }

    fun requestCountryListData() {
        countryListMediatorLiveData = MediatorLiveData()
        val stringLiveDta: LiveData<ArrayList<CountryListPojo>?>? = apiModel.getCountryList()

        countryListMediatorLiveData.addSource(
            stringLiveDta!!
        ) { t -> countryListMediatorLiveData.value = t }
    }

}