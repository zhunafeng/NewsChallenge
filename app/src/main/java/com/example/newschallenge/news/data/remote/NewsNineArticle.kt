package com.example.newschallenge.news.data.remote

import com.google.gson.annotations.SerializedName

data class NewsNineArticle(
    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("headline")
    var headline: String? = null,

    @SerializedName("theAbstract")
    var theAbstract: String? = null,

    @SerializedName("byLine")
    var byLine: String? = null,

    @SerializedName("relatedImages")
    var relatedImages: List<ArticleImage> = emptyList(),

    @SerializedName("timeStamp")
    var timeStamp: Long? = null,

    @SerializedName("smallestImage")
    var smallestImage: String? = null
)
