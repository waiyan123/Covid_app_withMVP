package com.itachi.covidapp.network.dataagents

import android.util.Log
import com.itachi.covidapp.data.vos.CountryStatVO
import com.itachi.covidapp.data.vos.WorldTotalVO
import com.itachi.covidapp.network.ApiEndPoints
import com.itachi.covidapp.network.Repo
import com.itachi.covidapp.network.responses.GetWorldDataCountryWiseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WorldTotalDataAgentImpl : WorldTotalDataAgent{

    private val api : ApiEndPoints = Repo.getInstance()

    override fun getWorldData(
        onSuccess: (GetWorldDataCountryWiseResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call = api.getWorldData()
        call.enqueue(object : Callback<GetWorldDataCountryWiseResponse> {
            override fun onFailure(call: Call<GetWorldDataCountryWiseResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetWorldDataCountryWiseResponse>,
                response: Response<GetWorldDataCountryWiseResponse>
            ) {
                if(response.isSuccessful) {

                    onSuccess(response.body()!!)

                }
            }

        })
    }

}