package com.itachi.covidapp.adapters.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    private val list : MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    fun addFragment(frag : Fragment) {
        list.add(frag)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}