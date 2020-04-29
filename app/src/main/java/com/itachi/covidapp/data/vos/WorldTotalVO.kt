package com.itachi.covidapp.data.vos

import com.google.gson.annotations.SerializedName

data class WorldTotalVO(

    @SerializedName("total_cases")
    val total_cases : String,

    @SerializedName("new_cases")
    val new_cases : String,

    @SerializedName("total_deaths")
    val total_deaths : String,

    @SerializedName("new_deaths")
    val new_deaths : String,

    @SerializedName("total_recovered")
    val total_recovered : String,

    @SerializedName("active_cases")
    val active_cases : String,

    @SerializedName("serious_critical")
    val serious_critical : String,

    @SerializedName("total_cases_per_1m_population")
    val total_cases_per_1m_population : String,

    @SerializedName("deaths_per_1m_population")
    val deaths_per_1m_population : String,

    @SerializedName("statistic_taken_at")
    val statistic_taken_at : String

    ) {
}