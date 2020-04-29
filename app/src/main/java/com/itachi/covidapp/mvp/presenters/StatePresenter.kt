package com.itachi.covidapp.mvp.presenters

import android.os.CountDownTimer
import com.itachi.covidapp.data.models.StateModel
import com.itachi.covidapp.data.models.StateModelImpl
import com.itachi.covidapp.data.models.WorldTotalModelImpl
import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.StateVO
import com.itachi.covidapp.data.vos.StatesVO
import com.itachi.covidapp.mvp.views.StateView

class StatePresenter : BasePresenter<StateView>() {


    private var mModel: StateModel = StateModelImpl
    private lateinit var stateList: StatesVO

    fun onUiReady() {

        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                mModel.getWorldData({
                    stateList = it.state_wise
                    mView.showStateData(stateList)
                },
                    {
                        mView.displayError(it)
                    })
            }
        }
        timer.start()


    }
}