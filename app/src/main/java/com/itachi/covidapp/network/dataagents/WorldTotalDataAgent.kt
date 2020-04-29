package com.itachi.covidapp.network.dataagents

import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO
import com.itachi.covidapp.network.responses.GetWorldDataCountryWiseResponse

interface WorldTotalDataAgent {

    fun getWorldData(
        onSuccess : (GetWorldDataCountryWiseResponse) -> Unit,
        onFailure: (String) -> Unit
    )

}