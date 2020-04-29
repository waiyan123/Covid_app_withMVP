package com.itachi.covidapp.data.models

import com.itachi.covidapp.network.dataagents.StateDataAgent
import com.itachi.covidapp.network.dataagents.StateDataAgentImpl
import com.itachi.covidapp.network.responses.GetDataStateAndDistrictWiseIndiaResponse

object StateModelImpl : StateModel{

    private val dataAgent : StateDataAgent = StateDataAgentImpl

    override fun getWorldData(
        onSuccess: (GetDataStateAndDistrictWiseIndiaResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dataAgent.getWorldData({
            onSuccess(it)
        },
            {
                onFailure(it)
            })
    }

}