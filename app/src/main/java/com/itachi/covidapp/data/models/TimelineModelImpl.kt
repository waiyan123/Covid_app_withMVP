package com.itachi.covidapp.data.models

import com.itachi.covidapp.data.vos.TimelineVO
import com.itachi.covidapp.network.dataagents.TimelineDataAgentImpl

object TimelineModelImpl : TimelineModel{

    private val dataAgent : TimelineDataAgentImpl = TimelineDataAgentImpl

    override fun getTimelineData(onSuccess: (List<TimelineVO>) -> Unit, onFailure: (String) -> Unit) {
        dataAgent.getTimelineData({
            onSuccess(it)
        },
            {
                onFailure(it)
            })
    }
}