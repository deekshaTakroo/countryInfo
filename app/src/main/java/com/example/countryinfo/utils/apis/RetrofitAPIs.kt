package com.example.countryinfo.utils.apis

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitAPIs {

    @Singleton
    @Provides
    fun provideGson() : Gson
    {
        var gsonBuilder = GsonBuilder()
        return  gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(configureTimeouts())
            .build()
    }

    fun configureTimeouts(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .build()
    }
}