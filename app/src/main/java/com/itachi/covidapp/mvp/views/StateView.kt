package com.itachi.covidapp.mvp.views

import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.StateVO
import com.itachi.covidapp.data.vos.StatesVO

interface StateView : BaseView{

    fun showStateData(data : StatesVO)

}