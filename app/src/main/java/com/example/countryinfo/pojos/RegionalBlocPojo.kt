package com.example.countryinfo.pojos

import com.google.gson.annotations.SerializedName

class RegionalBlocPojo {

    @SerializedName("acronym")
    val acronym: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("otherAcronyms")
    val otherAcronyms: List<Any>? = null
    @SerializedName("otherNames")
    val otherNames: List<Any>? = null
}