package com.itachi.covidapp.network.responses

import com.google.gson.annotations.SerializedName
import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO

class GetWorldDataCountryWiseResponse (

    @SerializedName("countries_stat")
    val countries_stat : ArrayList<CountryStatVO>,

    @SerializedName("statistic_taken_at")
    val statistic_taken_at : String,

    @SerializedName("world_total")
    val world_total : WorldTotalVO


){


}