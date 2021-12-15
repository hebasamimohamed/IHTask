package com.example.ihtask.repo

import com.example.ihtask.network.ApiHelper

class NewsRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers(q:String,from:String,sortBy:String,apiKey:String) = apiHelper.getUsers(q,from,sortBy,apiKey)
}