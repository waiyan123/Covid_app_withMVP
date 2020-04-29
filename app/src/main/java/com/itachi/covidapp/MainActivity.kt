package com.itachi.covidapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itachi.covidapp.adapters.pager.MainPagerAdapter
import com.itachi.covidapp.fragments.GlobelFragment
import com.itachi.covidapp.fragments.IndiaStateFragment
import com.itachi.covidapp.fragments.IndiaTimelineFragment
import com.itachi.covidapp.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView,
    BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

        mNavigationView.getMenu().getItem(position).setChecked(true)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId) {
            R.id.bot_nav_globel -> {
                mPager.setCurrentItem(0)
            }
            R.id.bot_nav_india_timeline -> {
                mPager.setCurrentItem(1)
            }
            R.id.bot_nav_india_state -> {
                mPager.setCurrentItem(2)
            }

        }
        return true
    }

    lateinit var mPager : ViewPager
    lateinit var mPagerAdapter : MainPagerAdapter
    lateinit var mNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val globelFragment = GlobelFragment(this)
        val indiaTimelineFragment = IndiaTimelineFragment(this)
        val indiaStateFragment = IndiaStateFragment(this)

        mPager = vp_globel
        mPagerAdapter = MainPagerAdapter(supportFragmentManager)
        mPagerAdapter.addFragment(globelFragment)
        mPagerAdapter.addFragment(indiaTimelineFragment)
        mPagerAdapter.addFragment(indiaStateFragment)
        mPager.adapter = mPagerAdapter

        mNavigationView = bnv_main
        mNavigationView.setOnNavigationItemSelectedListener(this)
        mPager.setOnPageChangeListener(this)

    }

    override fun getAppContext(): Context {
        return this.applicationContext
    }

    override fun displayError(str: String) {
    }

    override fun displayLoading() {
    }

    override fun hideLoading() {
    }

}
