package com.example.newschallenge.api

import com.example.newschallenge.news.data.remote.NewsNineService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@RunWith(JUnit4::class)
class NewsNineSourceServiceTest : BaseServiceTest() {

    private lateinit var service: NewsNineService

    @Before
    @Throws(IOException::class)
    fun createService() {
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsNineService::class.java)
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getNewsSource() = runBlocking {
        enqueueResponse("news_nine_source.json")
        val response = service.getTopHeadlines().body() ?: return@runBlocking

        // Dummy request
        mockWebServer.takeRequest()

        // Check news source
        assertThat(response, notNullValue())
        assertThat(response.url, `is`("/content/dam/lists/1/3/z/z/q/x/list.html"))
        assertThat(response.lastModified, `is`("1670808386087"))

        // Check list
        val articles = response.assets
        assertThat(articles, notNullValue())

        // Check item 1
        val article1 = articles[0]
        assertThat(article1, notNullValue())
        assertThat(article1.headline, `is`("The new PC that lets you show the office who’s boss"))
        assertThat(article1.theAbstract, `is`("Microsoft’s famous tilting PC is back, but it still has the same problem that plagued previous models."))
        assertThat(article1.byLine, `is`("John Davidson"))
        assertThat(article1.relatedImages, notNullValue())
    }
}