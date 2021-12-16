package com.example.ihtask.repo

import com.example.ihtask.network.RetrofitInstance

class AppRepository {

    suspend fun getLiveNews(q: String, from: String, sortBy: String) =
        RetrofitInstance.newsApi.getLiveNews(q, from, sortBy)

}