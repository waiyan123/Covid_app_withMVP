package com.itachi.covidapp.mvp.views

import android.content.Context

interface BaseView {

    fun getAppContext(): Context

    fun displayError(str: String)

    fun displayLoading()

    fun hideLoading()

}