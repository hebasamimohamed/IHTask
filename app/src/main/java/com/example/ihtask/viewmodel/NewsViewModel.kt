package com.example.ihtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ihtask.repo.NewsRepository
import com.example.ihtask.utils.Resource
import kotlinx.coroutines.Dispatchers

class NewsViewModel (private val mainRepository: NewsRepository) : ViewModel() {

    fun getLiveNews(q:String,from:String,sortBy:String,apiKey:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers(q,from,sortBy,apiKey)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}