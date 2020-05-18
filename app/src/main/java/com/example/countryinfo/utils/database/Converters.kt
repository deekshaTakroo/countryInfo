package com.example.countryinfo.utils.database

import androidx.room.TypeConverter
import com.example.countryinfo.pojos.CurrencyPojo
import com.example.countryinfo.pojos.LanguagePojo
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    companion object {
        @TypeConverter
        fun currencyFromString(value: String?): ArrayList<String>? {
            val listType: Type = object : TypeToken<ArrayList<CurrencyPojo?>?>() {}.type
            val currencyAl: ArrayList<CurrencyPojo>? = Gson().fromJson(value, listType)
            val currencyNameAl = ArrayList<String>()
            for (i in currencyAl!!.indices) {
                currencyNameAl.add(currencyAl[i].code!!)
            }
            return currencyNameAl
        }

        @TypeConverter
        fun stringFromCurrencyArrayList(list: List<CurrencyPojo>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter
        fun languageFromString(value: String?): ArrayList<String>? {
            val listType: Type = object : TypeToken<ArrayList<LanguagePojo?>?>() {}.type
            val languageAl : ArrayList<LanguagePojo>? = Gson().fromJson(value, listType)
            val languageNameAl = ArrayList<String>()
            for(i in languageAl!!.indices)
            {
                languageNameAl.add(languageAl[i].name!!)
            }
            return  languageNameAl
        }

        @TypeConverter
        fun stringFromLanguageArrayList(list: List<LanguagePojo?>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter
        fun latlongFromString(value: String?): ArrayList<Double>? {
            val listType: Type = object : TypeToken<ArrayList<Double?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun stringFromLatlongArrayList(list: List<Double>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }
    }

}