package com.itachi.covidapp.network.responses

import com.google.gson.annotations.SerializedName
import com.itachi.covidapp.data.vos.StateVO
import com.itachi.covidapp.data.vos.StatesVO

class GetDataStateAndDistrictWiseIndiaResponse (

    @SerializedName("state_wise")
    val state_wise : StatesVO

){

}