package com.itachi.covidapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.itachi.covidapp.R
import com.itachi.covidapp.data.vos.WorldTotalVO
import kotlinx.android.synthetic.main.globel_item.view.*

class GlobelViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {

    override fun setData(data: String, position: Int) {

        itemView.tv_header.text = itemView.context.resources.getStringArray(R.array.globel_items)[position]
        itemView.tv_body.text = data

    }


}