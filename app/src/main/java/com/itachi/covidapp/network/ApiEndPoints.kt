package com.itachi.covidapp.network

import com.itachi.covidapp.data.vos.TimelineVO
import com.itachi.covidapp.network.responses.GetDataStateAndDistrictWiseIndiaResponse
import com.itachi.covidapp.network.responses.GetWorldDataCountryWiseResponse
import com.itachi.covidapp.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiEndPoints {

    @GET(WORLD_DATA_END_POINT)
    fun getWorldData(@Header(HEADER_KEY)api_key : String ?= RAPID_API_KEY) : Call<GetWorldDataCountryWiseResponse>

    @GET(INDIA_TIMELINE_END_POINT)
    fun getTimelineData(@Header(HEADER_KEY)api_key : String ?= RAPID_API_KEY) : Call<List<TimelineVO>>

    @GET(INDIA_DATA_STATE_END_POINT)
    fun getStateData(@Header(HEADER_KEY)api_key: String?= RAPID_API_KEY) : Call<GetDataStateAndDistrictWiseIndiaResponse>
}