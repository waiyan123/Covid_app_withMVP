package com.itachi.covidapp.adapters.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.itachi.covidapp.R
import com.itachi.covidapp.data.vos.StateVO
import kotlinx.android.synthetic.main.list_group.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class CustomExpandableListAdapter(
    context: Context,
    titleList: List<String>,
    list: ArrayList<StateVO>
) : BaseExpandableListAdapter() {

    var mContext: Context = context
    var voList: ArrayList<StateVO> = list
    var expandableListTitle: List<String> = titleList

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return voList[expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int, expandedListPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false)
        view.tv_active.text = "Active - "+voList[listPosition].active
        view.tv_confirmed.text = "Confirmed - "+voList[listPosition].confirmed
        view.tv_deaths.text = "Deaths - "+voList[listPosition].deaths
        view.tv_recovered.text = "Recovered - "+voList[listPosition].recovered
        view.tv_lastupdatedtime.text = "Last updated time - "+voList[listPosition].lastupdatedtime
        return view
    }

    override fun getChildrenCount(p0: Int): Int {
        return 1
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_group,parent,false)
        view.tv_list_title.text = expandableListTitle[listPosition]
        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

}