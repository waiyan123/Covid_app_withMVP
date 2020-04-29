package com.itachi.covidapp.network.dataagents

import android.util.Log
import com.itachi.covidapp.data.vos.TimelineVO
import com.itachi.covidapp.network.ApiEndPoints
import com.itachi.covidapp.network.Repo
import com.itachi.covidapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TimelineDataAgentImpl : TimelineDataAgent{

    private val api : ApiEndPoints = Repo.getInstance()

    override fun getTimelineData(
        onSuccess: (List<TimelineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call = api.getTimelineData()
        call.enqueue(object : Callback<List<TimelineVO>> {
            override fun onFailure(call: Call<List<TimelineVO>>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<List<TimelineVO>>,
                response: Response<List<TimelineVO>>
            ) {
                if(response.isSuccessful) {
                    Log.d("test---","successful")
                    onSuccess(response.body() as List<TimelineVO>)
                }
                else {
                    onFailure("Something went wrong!")
                }
            }

        })
    }
}