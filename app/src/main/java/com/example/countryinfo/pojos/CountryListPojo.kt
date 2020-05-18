package com.example.countryinfo.pojos

import com.google.gson.annotations.SerializedName

class CountryListPojo {
    @SerializedName("name")
    val name: String? = null
    @SerializedName("topLevelDomain")
    val topLevelDomain: List<String>? = null
    @SerializedName("alpha2Code")
    val alpha2Code: String? = null
    @SerializedName("alpha3Code")
    val alpha3Code: String? = null
    @SerializedName("callingCodes")
    val callingCodes: List<String>? = null
    @SerializedName("capital")
    val capital: String? = null
    @SerializedName("altSpellings")
    val altSpellings: List<String>? = null
    @SerializedName("region")
    val region: String? = null
    @SerializedName("subregion")
    val subregion: String? = null
    @SerializedName("population")
    val population: Int? = null
    @SerializedName("latlng")
    val latlng: List<Double>? = null
    @SerializedName("demonym")
    val demonym: String? = null
    @SerializedName("area")
    val area: Double? = null
    @SerializedName("gini")
    val gini: Double? = null
    @SerializedName("timezones")
    val timezones: List<String>? = null
    @SerializedName("borders")
    val borders: List<String>? = null
    @SerializedName("nativeName")
    val nativeName: String? = null
    @SerializedName("numericCode")
    val numericCode: String? = null
    @SerializedName("currencies")
    val currencies: List<CurrencyPojo>? = null
    @SerializedName("languages")
    val languages: List<LanguagePojo>? = null
    @SerializedName("translations")
    val translations: TranslationsPojo? = null
    @SerializedName("flag")
    val flag: String? = null
    @SerializedName("regionalBlocs")
    val regionalBlocs: List<RegionalBlocPojo>? = null
    @SerializedName("cioc")
    val cioc: String? = null
}