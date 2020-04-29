package com.itachi.covidapp.network.dataagents

import com.itachi.covidapp.network.ApiEndPoints
import com.itachi.covidapp.network.Repo
import com.itachi.covidapp.network.responses.GetDataStateAndDistrictWiseIndiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object StateDataAgentImpl : StateDataAgent{

    private val api : ApiEndPoints = Repo.getInstance()

    override fun getWorldData(
        onSuccess: (GetDataStateAndDistrictWiseIndiaResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call = api.getStateData()
        call.enqueue(object : Callback<GetDataStateAndDistrictWiseIndiaResponse>{
            override fun onFailure(
                call: Call<GetDataStateAndDistrictWiseIndiaResponse>,
                t: Throwable
            ) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetDataStateAndDistrictWiseIndiaResponse>,
                response: Response<GetDataStateAndDistrictWiseIndiaResponse>
            ) {
                if(response.isSuccessful){
                    onSuccess(response.body()!!)
                }
                else onFailure("Something went wrong in State Response")
            }

        })
    }
}