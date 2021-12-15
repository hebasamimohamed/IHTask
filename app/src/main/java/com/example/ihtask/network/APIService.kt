package com.example.ihtask.network

import com.example.ihtask.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    @GET("v2/everything")
    fun getLiveNews(
        @Query("q") country: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") apiKey: String?
    ): Call<NewsResponse>
}