package com.itachi.covidapp.data.vos

import com.google.gson.annotations.SerializedName

data class TimelineVO(

    @SerializedName("dailyconfirmed")
    val dailyconfirmed : String ,

    @SerializedName("dailydeceased")
    val dailydeceased : String ,

    @SerializedName("dailyrecovered")
    val dailyrecovered : String ,

    @SerializedName("date")
    val date : String ,

    @SerializedName("totalconfirmed")
    val totalconfirmed : String ,

    @SerializedName("totaldeceased")
    val totaldeceased : String ,

    @SerializedName("totalrecovered")
    val totalrecovered : String
) {

}