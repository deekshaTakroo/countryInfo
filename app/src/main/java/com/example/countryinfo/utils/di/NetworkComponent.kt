package com.example.countryinfo.utils.di

import com.example.countryinfo.utils.apis.APIModel
import com.example.countryinfo.utils.apis.RetrofitAPIs
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitAPIs::class])
interface NetworkComponent {

    fun inject(apiModel: APIModel)
}