package com.itachi.covidapp.mvp.presenters

import android.os.CountDownTimer
import com.itachi.covidapp.data.models.TimelineModelImpl
import com.itachi.covidapp.mvp.views.TimelineView

class TimelinePresenter : BasePresenter<TimelineView>(){

    private var mModel : TimelineModelImpl = TimelineModelImpl

    fun onUiReady() {
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                mModel.getTimelineData({
                    mView.showTimelineData(it)
                },
                    {
                        mView.displayError(it)
                    })
            }
        }
        timer.start()

    }
}