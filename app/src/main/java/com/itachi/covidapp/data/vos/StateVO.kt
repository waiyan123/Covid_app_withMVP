package com.itachi.covidapp.data.vos

import com.google.gson.annotations.SerializedName

data class StateVO(

    @SerializedName("active")
    val active : String,

    @SerializedName("confirmed")
    val confirmed : String,

    @SerializedName("deaths")
    val deaths : String,

    @SerializedName("deltaconfirmed")
    val deltaconfirmed : String,

    @SerializedName("deltadeaths")
    val deltadeaths : String,

    @SerializedName("deltarecovered")
    val deltarecovered : String,

    @SerializedName("lastupdatedtime")
    val lastupdatedtime : String,

    @SerializedName("recovered")
    val recovered : String,

    @SerializedName("state")
    val state : String,

    @SerializedName("statecode")
    val statecode : String,

    @SerializedName("statenotes")
    val statenotes : String

) {

}



