package com.itachi.covidapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_india_timeline.*
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.itachi.covidapp.custom.InMemoryCursor
import com.itachi.covidapp.data.vos.TimelineVO
import com.itachi.covidapp.mvp.presenters.TimelinePresenter
import com.itachi.covidapp.mvp.views.TimelineView
import com.ruesga.timelinechart.TimelineChartView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.widget.TextView
import android.graphics.Color
import com.itachi.covidapp.R
import kotlinx.android.synthetic.main.fragment_india_timeline.view.*
import kotlinx.android.synthetic.main.serie_item_layout.view.*
import java.text.DecimalFormat




class IndiaTimelineFragment(context : Context) : Fragment(),TimelineView,
    TimelineChartView.OnSelectedItemChangedListener,
    TimelineChartView.OnColorPaletteChangedListener {

    val mContext : Context = context

    override fun onColorPaletteChanged(palette: IntArray?) {
        val count = mSeriesColors.size
        for (i in 0 until count) {
            mSeriesColors[i]!!.setBackgroundColor(palette!![i])
        }
    }

    private val DATETIME_FORMATTER = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val NUMBER_FORMATTER = DecimalFormat("#0.00")

    lateinit var mSeries: Array<TextView?>
    lateinit var mSeriesColors: Array<View?>

    override fun onSelectedItemChanged(selectedItem: TimelineChartView.Item?, fromUser: Boolean) {
        item_timestamp.text = DATETIME_FORMATTER.format(selectedItem!!.mTimestamp)

        for (i in 0 until mSeries.size) {
            mSeries[i]!!.text = NUMBER_FORMATTER.format(selectedItem.mSeries[i])
//            Log.d("test---"," $i = ${selectedItem.mSeries[i]}" )
        }
    }

    override fun onNothingSelected() {

    }

    val COLUMN_NAMES =  arrayOf("Timestamp","Daily Confirmed","Total Recovered","Total Confirmed")

    lateinit var mStart : Calendar

    override fun showTimelineData(list: List<TimelineVO>) {

        mCursor = createInMemoryCursor(list)
        graph.observeData(mCursor)

        graph.addOnSelectedItemChangedListener(this)
        graph.addOnColorPaletteChangedListener(this)

    }

    override fun getAppContext(): Context {
        return context!!.applicationContext
    }

    override fun displayError(str: String) {
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading() {

    }

    override fun hideLoading() {

    }

    lateinit var mPresenter : TimelinePresenter
    lateinit var mCursor : InMemoryCursor


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView = inflater.inflate(R.layout.fragment_india_timeline,container,false)

        mSeries = arrayOfNulls(COLUMN_NAMES.size-1)
        mSeriesColors = arrayOfNulls(COLUMN_NAMES.size-1)

        for (i in 1 until COLUMN_NAMES.size) {
            val v = inflater.inflate(R.layout.serie_item_layout, mView.item_series, false)
            v.title.text = COLUMN_NAMES[i]
            mSeries[i - 1] = v.findViewById(R.id.value)
            mSeries[i - 1]!!.text = "-"
            mSeriesColors[i - 1] = v.findViewById(R.id.color)
            mSeriesColors[i - 1]!!.setBackgroundColor(Color.TRANSPARENT)
            mView.item_series.addView(v)
        }

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = TimelinePresenter()
        mPresenter.initPresenter(this)
        mPresenter.onUiReady()


    }

    private fun createItem(timestamp: Long,vo : TimelineVO): Array<Any> {
        val item = Array(COLUMN_NAMES.size){Any()}
        item[0] = timestamp
        item[1] = vo.dailyconfirmed.toInt()
        item[2] = vo.totalrecovered.toInt()
        item[3] = vo.totalconfirmed.toInt()

        return item
    }

    private fun createInMemoryCursor(list : List<TimelineVO>): InMemoryCursor {
        val cursor = InMemoryCursor(COLUMN_NAMES)
        createRandomData(cursor,list)
        return cursor
    }

    private fun createRandomData(cursor: InMemoryCursor,list : List<TimelineVO>) {
        val data = ArrayList<Array<Any>>()
        val today = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
        today.set(Calendar.HOUR_OF_DAY, 0)
        today.set(Calendar.MINUTE, 0)
        today.set(Calendar.SECOND, 0)
        today.set(Calendar.MILLISECOND, 0)
        mStart = today.clone() as Calendar
        mStart.add(Calendar.DAY_OF_YEAR, -list.size)
//        Log.d("test---","size "+list.size)

        var inte = 0
        for(i in 0 until list.size) {

//            Log.d("test---","$inte")
            inte++

//            Log.d("test---"," dailyConfirmed ${list[i].dailyconfirmed} " +
//                    "totalConfirmed ${list[i].totalconfirmed} totalRecovered ${list[i].totalrecovered}")
            data.add(createItem(mStart.timeInMillis,list[i]))
            mStart.add(Calendar.DAY_OF_YEAR, 1)
        }
        cursor.addAll(data)
    }
}