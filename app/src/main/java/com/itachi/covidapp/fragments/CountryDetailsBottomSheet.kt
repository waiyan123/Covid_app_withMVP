package com.itachi.covidapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itachi.covidapp.R
import com.itachi.covidapp.data.vos.CountryStatVO
import kotlinx.android.synthetic.main.country_details_bottom_sheet.*

class CountryDetailsBottomSheet(countryVO : CountryStatVO) : BottomSheetDialogFragment(){

    var vo : CountryStatVO = countryVO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_details_bottom_sheet,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_country_name.text = vo.country_name
        tv_cases.text = vo.cases
        tv_deaths.text = vo.deaths
        tv_region.text = vo.region
        tv_total_recovered.text = vo.total_recovered
        tv_new_deaths.text = vo.new_deaths
        tv_new_cases.text = vo.new_cases
        tv_serious_critical.text = vo.serious_critical
        tv_active_cases.text = vo.active_cases
        tv_total_cases_per_population.text = vo.total_cases_per_1m_population
        tv_death_cases_per_population.text = vo.deaths_per_1m_population
        tv_total_tests.text = vo.total_tests
        tv_tests_per_population.text = vo.tests_per_1m_population
    }
}