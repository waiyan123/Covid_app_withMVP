package com.itachi.covidapp.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itachi.covidapp.R
import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.viewholders.CountryViewHolder
import com.itachi.covidapp.viewholders.GlobelViewHolder

class CountriesRecyclerAdapter(list:ArrayList<CountryStatVO>, private val adapterOnClick : (Int) -> Unit) : RecyclerView.Adapter<CountryViewHolder>(){

    var dataList = ArrayList<CountryStatVO>()

    init {
        dataList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return CountryViewHolder(itemView,adapterOnClick)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.setData(dataList[position],position)
    }

}