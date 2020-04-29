package com.itachi.covidapp.data.models

import com.itachi.covidapp.data.vos.TimelineVO
import com.itachi.covidapp.network.responses.GetWorldDataCountryWiseResponse

interface TimelineModel {

    fun getTimelineData(
        onSuccess : (List<TimelineVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}