package com.itachi.covidapp.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryStatVO(

    @SerializedName("country_name")
    val country_name : String,

    @SerializedName("cases")
    val cases : String,

    @SerializedName("deaths")
    val deaths : String,

    @SerializedName("region")
    val region : String,

    @SerializedName("total_recovered")
    val total_recovered : String,

    @SerializedName("new_deaths")
    val new_deaths : String,

    @SerializedName("new_cases")
    val new_cases : String,

    @SerializedName("serious_critical")
    val serious_critical : String,

    @SerializedName("active_cases")
    val active_cases : String,

    @SerializedName("total_cases_per_1m_population")
    val total_cases_per_1m_population : String,

    @SerializedName("deaths_per_1m_population")
    val deaths_per_1m_population : String,

    @SerializedName("total_tests")
    val total_tests : String,

    @SerializedName("tests_per_1m_population")
    val tests_per_1m_population : String

) : Serializable{
}