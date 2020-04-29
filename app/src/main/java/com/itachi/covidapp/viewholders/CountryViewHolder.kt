package com.itachi.covidapp.viewholders

import android.view.View
import com.itachi.covidapp.R
import com.itachi.covidapp.data.vos.CountryStatVO
import kotlinx.android.synthetic.main.country_item.view.*
import kotlinx.android.synthetic.main.globel_item.view.*

class CountryViewHolder(itemView: View,val adapterOnClick : (Int) -> Unit) : BaseViewHolder<CountryStatVO>(itemView) {


    override fun setData(data: CountryStatVO, position: Int) {

        itemView.tv_country_name.text = data.country_name

        itemView.setOnClickListener {
            adapterOnClick(position)
        }
    }


}