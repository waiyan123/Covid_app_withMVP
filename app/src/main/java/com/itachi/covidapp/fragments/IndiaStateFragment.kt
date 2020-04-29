package com.itachi.covidapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itachi.covidapp.R
import com.itachi.covidapp.adapters.list.CustomExpandableListAdapter
import com.itachi.covidapp.data.vos.StateVO
import com.itachi.covidapp.data.vos.StatesVO
import com.itachi.covidapp.mvp.presenters.GlobelPresenter
import com.itachi.covidapp.mvp.presenters.StatePresenter
import com.itachi.covidapp.mvp.views.StateView
import kotlinx.android.synthetic.main.fragment_india_state.*

class IndiaStateFragment : Fragment(),StateView{

    override fun showStateData(data: StatesVO) {
        Log.d("test---","delhi state data ${data.Delhi.confirmed}")

        val voList = ArrayList<StateVO>()
        voList.add(data.Maharashtra)
        voList.add(data.Gujarat)
        voList.add(data.Delhi)
        voList.add(data.Rajasthan)
        voList.add(data.Madhya_Pradesh)
        voList.add(data.Tamil_Nadu)
        voList.add(data.Uttar_Pradesh)
        voList.add(data.Andhra_Pradesh)
        voList.add(data.Telangana)
        voList.add(data.West_Bengal)
        voList.add(data.Jammu_and_Kashmir)
        voList.add(data.Karnataka)
        voList.add(data.Kerala)
        voList.add(data.Bihar)
        voList.add(data.Punjab)
        voList.add(data.Haryana)
        voList.add(data.Odisha)
        voList.add(data.Jharkhand)
        voList.add(data.Chandigarh)
        voList.add(data.Uttarakhand)
        voList.add(data.Himachal_Pradesh)
        voList.add(data.Assam)
        voList.add(data.Chhattisgarh)
        voList.add(data.Andaman_and_Nicobar_Islands)
        voList.add(data.Ladakh)
        voList.add(data.Meghalaya)
        voList.add(data.Puducherry)
        voList.add(data.Goa)
        voList.add(data.Manipur)
        voList.add(data.Tripura)
        voList.add(data.Mizoram)
        voList.add(data.Arunachal_Pradesh)
        voList.add(data.Nagaland)
        voList.add(data.Dadra_and_Nagar_Haveli)
        voList.add(data.Daman_and_Diu)
        voList.add(data.Lakshadweep)
        voList.add(data.Sikkim)
        expandableListAdapter = CustomExpandableListAdapter(getAppContext(),
            resources.getStringArray(R.array.expandable_list_items).toList(),voList)
        expandableListView.setAdapter(expandableListAdapter)

    }

    override fun getAppContext(): Context {
        return context!!.applicationContext
    }

    override fun displayError(str: String) {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading() {

    }

    override fun hideLoading() {

    }

    lateinit var mPresenter : StatePresenter
    lateinit var expandableListAdapter : CustomExpandableListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_india_state,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mPresenter = ViewModelProviders.of(this).get(StatePresenter::class.java)
        mPresenter.initPresenter(this@IndiaStateFragment)
        mPresenter.onUiReady()
    }
}