package com.itachi.covidapp.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itachi.covidapp.R
import com.itachi.covidapp.viewholders.GlobelViewHolder

class GlobelRecyclerAdapter(list:ArrayList<String>) : RecyclerView.Adapter<GlobelViewHolder>(){

    var dataList = ArrayList<String>()

    init {
        dataList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.globel_item,parent,false)
        return GlobelViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 9
    }

    override fun onBindViewHolder(holder: GlobelViewHolder, position: Int) {
        holder.setData(dataList[position],position)
    }

}