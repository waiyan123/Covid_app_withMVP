package com.itachi.covidapp.mvp.presenters

import androidx.lifecycle.ViewModel
import com.itachi.covidapp.mvp.views.BaseView

abstract class BasePresenter<T : BaseView> : ViewModel() {

    protected lateinit var mView : T

    fun initPresenter(view : T) {
        mView = view
    }
}