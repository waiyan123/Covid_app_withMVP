package com.itachi.covidapp.data.models

import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO
import com.itachi.covidapp.network.dataagents.WorldTotalDataAgentImpl
import com.itachi.covidapp.network.responses.GetWorldDataCountryWiseResponse

object WorldTotalModelImpl : WorldTotalModel{

    private val dataAgent : WorldTotalDataAgentImpl = WorldTotalDataAgentImpl

    override fun getWorldData(
        onSuccess: (GetWorldDataCountryWiseResponse) -> Unit,
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