package com.example.newschallenge.news.api

import com.google.gson.annotations.SerializedName

/**
 * Describes the response from given API.
 */
data class NewsNineResponse(

    @SerializedName("id")
    val id: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("lastModified")
    val lastModified: String = "",

    @SerializedName("assets")
    val assets: List<NewsNineArticle> = emptyList(),
)
