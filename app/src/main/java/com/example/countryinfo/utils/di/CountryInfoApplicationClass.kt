package com.example.countryinfo.utils.di

import android.app.Application
import android.content.Context


class CountryInfoApplicationClass : Application() {

    companion object{
        lateinit var networkComponent : NetworkComponent
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        networkComponent = DaggerNetworkComponent.builder().build()
    }


}