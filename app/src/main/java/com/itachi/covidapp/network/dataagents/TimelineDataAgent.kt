package com.itachi.covidapp.network.dataagents

import com.itachi.covidapp.data.vos.TimelineVO

interface TimelineDataAgent {

    fun getTimelineData(
        onSuccess : (List<TimelineVO>) -> Unit,
        onFailure : (String) -> Unit
    )
}