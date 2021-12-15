package com.example.ihtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ihtask.network.ApiHelper
import com.example.ihtask.repo.NewsRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(NewsRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}