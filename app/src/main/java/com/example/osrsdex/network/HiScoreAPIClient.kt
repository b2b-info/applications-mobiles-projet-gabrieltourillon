package com.example.osrsdex.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HiScoreAPIClient {
    private var retrofit: Retrofit? = null
    private var client: OkHttpClient? = null
    private const val apiUrl = "https://osrsdex.azurewebsites.net/"
    private val gson = GsonBuilder().setLenient().create()
    fun getInstance(): Retrofit {
        // Ajout d'un interceptor pour les logs
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        if (client == null) {
            client = OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build()
        }
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(apiUrl)
                // we need to add converter factory to
                // convert JSON object to Kotlin data object
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
        return retrofit!!
    }

}