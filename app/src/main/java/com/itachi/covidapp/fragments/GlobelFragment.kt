package com.itachi.covidapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itachi.covidapp.R
import com.itachi.covidapp.adapters.recycler.CountriesRecyclerAdapter
import com.itachi.covidapp.adapters.recycler.GlobelRecyclerAdapter
import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO
import com.itachi.covidapp.mvp.presenters.GlobelPresenter
import com.itachi.covidapp.mvp.views.GlobelView
import kotlinx.android.synthetic.main.fragment_globel.*

class GlobelFragment : Fragment(),GlobelView {

    override fun onClickItem(countryStatVO: CountryStatVO) {
//        val key = CountryDetailsBottomSheet.EXTRA_EVENT_ID
//        val intent = CountryDetailsBottomSheet.newIntent(context!!)
//        intent.putExtra(key,countryStatVO)
//        startActivity(intent)

        val detailsBottomSheet = CountryDetailsBottomSheet(countryStatVO)
        detailsBottomSheet.show(fragmentManager,"BottomSheetDialog")
    }

    override fun showCountryStatData(data: ArrayList<CountryStatVO>) {

        val layoutManager = LinearLayoutManager(getAppContext(),LinearLayoutManager.HORIZONTAL,false)

        countryRecyclerView = rv_countries
        countryRecyclerAdapter = CountriesRecyclerAdapter(data) {
            mPresenter.navigateToCountryDetails(it)
        }
        countryRecyclerView.layoutManager = layoutManager
        countryRecyclerView.adapter = countryRecyclerAdapter
    }

    override fun showStatisticTime(time: String) {
        tv_data_updated_time.text = "World Record Updated Time - $time"
    }

    override fun showWorldTotalVO(data: WorldTotalVO) {
        val list = ArrayList<String>()
        list.add(data.total_cases)
        list.add(data.new_cases)
        list.add(data.total_deaths)
        list.add(data.new_deaths)
        list.add(data.total_recovered)
        list.add(data.active_cases)
        list.add(data.serious_critical)
        list.add(data.total_cases_per_1m_population)
        list.add(data.deaths_per_1m_population)
        list.add(data.statistic_taken_at)

        val layoutManager = GridLayoutManager(getAppContext(), 2)

        globalRecyclerView = rv_globel
        globalRecyclerAdapter = GlobelRecyclerAdapter(list)
        globalRecyclerView.layoutManager = layoutManager
        globalRecyclerView.adapter = globalRecyclerAdapter
    }

    override fun getAppContext(): Context {
        return context!!.applicationContext
    }

    override fun displayError(str: String) {

    }

    override fun displayLoading() {

    }

    override fun hideLoading() {

    }

    lateinit var mPresenter : GlobelPresenter
    lateinit var globalRecyclerView : RecyclerView
    lateinit var countryRecyclerView : RecyclerView
    lateinit var globalRecyclerAdapter : GlobelRecyclerAdapter
    lateinit var countryRecyclerAdapter: CountriesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_globel,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mPresenter = ViewModelProviders.of(this).get(GlobelPresenter::class.java)
        mPresenter.initPresenter(this@GlobelFragment)
        mPresenter.onUiReady()
    }
}