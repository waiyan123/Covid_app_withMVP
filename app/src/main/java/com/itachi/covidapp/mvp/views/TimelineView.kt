package com.itachi.covidapp.mvp.views

import com.itachi.covidapp.data.vos.TimelineVO

interface TimelineView : BaseView{

    fun showTimelineData(list : List<TimelineVO>)

}