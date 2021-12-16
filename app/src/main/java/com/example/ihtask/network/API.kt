package com.example.ihtask.network

import com.example.ihtask.models.NewsResponse
import com.example.ihtask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface API {
    @Headers("X-Api-Key:" + Constants.API_KEY)
    @GET("v2/everything")
    suspend fun getLiveNews(
        @Query("q") country: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
    ): Response<NewsResponse>

}