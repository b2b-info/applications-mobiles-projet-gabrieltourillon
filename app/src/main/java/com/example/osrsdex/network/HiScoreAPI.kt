package com.example.osrsdex.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface HiScoreAPI {
    @GET("/api/getMyStatsGameMode")
    suspend fun getMyStatsGameMode(): Response<ResponseBody>

    @GET("/api/?results=10")
    suspend fun generateUsers(): Response<ResponseBody>
}