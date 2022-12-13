package com.example.newschallenge.news.api

import com.google.gson.annotations.SerializedName

data class ArticleImage(

    @SerializedName("id")
    var id: String,
    @SerializedName("url")
    var url: String,

    @SerializedName("width")
    var width: Long,

    @SerializedName("height")
    var height: Long,

    @SerializedName("timeStamp")
    var timeStamp: String,

    @SerializedName("imageSize")
    var imageSize: Long
)
