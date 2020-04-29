package com.itachi.covidapp.network.dataagents

import com.itachi.covidapp.network.responses.GetDataStateAndDistrictWiseIndiaResponse

interface StateDataAgent {

    fun getWorldData(
        onSuccess : (GetDataStateAndDistrictWiseIndiaResponse) -> Unit,
        onFailure: (String) -> Unit
    )

}