package com.itachi.covidapp.data.models

import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO
import com.itachi.covidapp.network.responses.GetWorldDataCountryWiseResponse

interface WorldTotalModel {

    fun getWorldData(
        onSuccess : (GetWorldDataCountryWiseResponse) -> Unit,
        onFailure: (String) -> Unit
    )

}