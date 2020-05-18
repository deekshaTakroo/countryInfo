package com.example.countryinfo.utils.database

import androidx.room.*
import com.example.countryinfo.pojos.CurrencyPojo
import com.example.countryinfo.pojos.LanguagePojo
import java.io.Serializable

@Entity(indices = [Index(value = ["name"] , unique = true)])
class Task :Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "flag")
    var flag: String? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "population")
    var population: String? = null

    //@TypeConverters(Converters::class)
    @ColumnInfo(name = "languageObject")
    var languageObject: String? = null

    @ColumnInfo(name = "capital")
    var capital: String? = null

   // @TypeConverters(Converters::class)
    @ColumnInfo(name = "latlong")
    var latlong: String? = null

   // @TypeConverters(Converters::class)
    @ColumnInfo(name = "currencyObject")
    var currencyObject: String? = null


}