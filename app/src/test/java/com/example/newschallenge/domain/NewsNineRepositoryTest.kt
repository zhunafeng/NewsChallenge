package com.example.newschallenge.domain

import com.example.newschallenge.news.data.remote.NewsNineArticle
import com.example.newschallenge.news.data.remote.NewsNineResponse
import com.example.newschallenge.news.data.remote.NewsNineService
import com.example.newschallenge.news.data.local.NewsNineArticlesDao
import com.example.newschallenge.news.data.local.NewsNineArticleDb
import com.example.newschallenge.utils.assertItems
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import com.example.newschallenge.news.data.DefaultNewsNineRepository
import com.example.newschallenge.news.presentations.ViewState

@RunWith(JUnit4::class)
class NewsNineRepositoryTest {

    @Mock
    lateinit var newsDao: NewsNineArticlesDao

    @Mock
    lateinit var newsSourceService: NewsNineService

    @InjectMocks
    lateinit var newsRepository: DefaultNewsNineRepository

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get news articles from web when there is internet`() = runBlocking {
        // GIVEN
        val fetchedArticles = listOf(
            NewsNineArticle(headline = "Fetched 1", theAbstract = "fetchedArticles"),
            NewsNineArticle(headline = "Fetched 2", theAbstract = "fetchedArticles")
        )
        val cachedArticles = listOf(
            NewsNineArticleDb(headline = "Fetched 1", theAbstract = "cachedTheAbstract"),
            NewsNineArticleDb(headline = "Fetched 2", theAbstract = "cachedTheAbstract")
        )
        val newsSource = NewsNineResponse(assets = fetchedArticles)
        val response = Response.success(newsSource)

        // WHEN
        whenever(newsSourceService.getTopHeadlines()) doReturn response
        whenever(newsDao.getNewsArticles()) doReturn flowOf(cachedArticles)

        // THEN
        newsRepository.getNewsArticles().assertItems(
            ViewState.loading(),
            ViewState.success(cachedArticles)
        )
    }

    @Test
    fun `get cached news articles when there is no internet`() = runBlocking {
        // GIVEN
        val cachedArticles =
            listOf(NewsNineArticleDb(headline = "Cached", theAbstract = "cachedTheAbstract"))
        val error = RuntimeException("Unable to fetch from network")

        // WHEN
        whenever(newsSourceService.getTopHeadlines()) doThrow error
        whenever(newsDao.getNewsArticles()) doReturn flowOf(cachedArticles)

        // THEN
        newsRepository.getNewsArticles().assertItems(
            ViewState.loading(),
            ViewState.success(cachedArticles)
        )
    }
}