package com.example.ihtask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ihtask.R
import com.example.ihtask.app.MyApplication
import com.example.ihtask.models.NewsResponse
import com.example.ihtask.repo.AppRepository
import com.example.ihtask.utils.Resource
import com.example.ihtask.utils.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {
    val articlesDATA: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getArticles()
    }

    fun getArticles() = viewModelScope.launch {
        fetchArticles()
    }


    private suspend fun fetchArticles() {
        articlesDATA.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getLiveNews(
                    "apple", "2021-11-16", "publishedAt", ""
                )
                articlesDATA.postValue(handlePicsResponse(response))
            } else {
                articlesDATA.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> articlesDATA.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.network_failure
                        )
                    )
                )
                else -> articlesDATA.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.conversion_error
                        )
                    )
                )
            }
        }
    }
    private fun handlePicsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}