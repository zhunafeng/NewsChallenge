package com.example.newschallenge.news.api

import retrofit2.Response
import retrofit2.http.GET

/**
 *
 * Describes endpoints to fetch the news from NewsAPI.
 *
 */
interface NewsNineService {

    /**
     * Get top headlines.
     */
    @GET("coding_test/13ZZQX/full")
    suspend fun getTopHeadlines(): Response<NewsNineResponse>

}
