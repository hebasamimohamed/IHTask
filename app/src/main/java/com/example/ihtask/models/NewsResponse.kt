package com.example.ihtask.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(@SerializedName("status") val status : String,
                        @SerializedName("totalResults") val totalResults : Int,
                        @SerializedName("articles") val articles : List<Articles>
):Serializable
