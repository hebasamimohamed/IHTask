package com.example.ihtask.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Timestamp

data class Articles(@SerializedName("source") val source : Source,
                    @SerializedName("author") val author : String,
                    @SerializedName("title") val title : String,
                    @SerializedName("description") val description : String,
                    @SerializedName("url") val url : String,
                    @SerializedName("urlToImage") val urlToImage : String,
                    @SerializedName("publishedAt") val publishedAt : Timestamp,
                    @SerializedName("content") val content : String
):Serializable
