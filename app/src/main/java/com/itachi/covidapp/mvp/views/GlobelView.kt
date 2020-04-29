package com.itachi.covidapp.mvp.views

import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO

interface GlobelView : BaseView{

    fun showCountryStatData(data : ArrayList<CountryStatVO>)

    fun showStatisticTime(time : String)

    fun showWorldTotalVO(data : WorldTotalVO)

    fun onClickItem(countryStatVO: CountryStatVO)

}