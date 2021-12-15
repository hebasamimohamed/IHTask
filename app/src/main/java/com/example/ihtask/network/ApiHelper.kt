package com.example.ihtask.network

class ApiHelper(private val apiService: APIService) {

    suspend fun getUsers(q:String,from:String,sortBy:String,apiKey:String) = apiService.getLiveNews(q,from,sortBy,apiKey)
}