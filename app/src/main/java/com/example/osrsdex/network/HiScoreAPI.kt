package com.example.osrsdex.network

import com.example.example.HiScoreDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HiScoreAPI {

    /**
     *
     */
    @GET("/api/getStatsByGameMode")
    suspend fun getStatsByGameMode(@Query("name") name: String): Response<HiScoreDataClass>


    /**
     * Runs getMyStatsByGameMode on the name "Gabeypoo"
     * More of a testing function than anything.
     */
    @GET("/api/getMyStatsByGameMode")
    suspend fun getMyStatsByGameMode(): Response<HiScoreDataClass>


}