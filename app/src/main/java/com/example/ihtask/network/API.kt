package com.example.ihtask.network

import com.example.ihtask.models.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface API {
    @Headers("X-Api-Key:" + "d4320324d09b4b81b0190d57b235df90")
    @GET("v2/everything")
    suspend   fun getLiveNews(
        @Query("q") country: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
    ): Response<NewsResponse>

}