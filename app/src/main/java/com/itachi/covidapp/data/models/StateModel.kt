package com.itachi.covidapp.data.models

import com.itachi.covidapp.network.responses.GetDataStateAndDistrictWiseIndiaResponse

interface StateModel {

    fun getWorldData(
        onSuccess : (GetDataStateAndDistrictWiseIndiaResponse) -> Unit,
        onFailure: (String) -> Unit
    )
}