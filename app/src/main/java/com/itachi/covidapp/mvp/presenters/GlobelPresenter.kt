package com.itachi.covidapp.mvp.presenters

import com.itachi.covidapp.data.models.WorldTotalModelImpl
import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.mvp.views.GlobelView

class GlobelPresenter : BasePresenter<GlobelView>(){

    private var mModel : WorldTotalModelImpl = WorldTotalModelImpl
    private lateinit var countriesList : ArrayList<CountryStatVO>

    fun onUiReady() {
        mModel.getWorldData({
            mView.showCountryStatData(it.countries_stat)
            mView.showStatisticTime(it.statistic_taken_at)
            mView.showWorldTotalVO(it.world_total)
            countriesList = it.countries_stat
        },
            {
                mView.displayError(it)
            })
    }

    fun navigateToCountryDetails(index : Int) {
        mView.onClickItem(countriesList[index])
    }
}