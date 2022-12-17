package com.example.newschallenge.news.data

import com.example.newschallenge.news.data.local.NewsNineArticleDb
import com.example.newschallenge.news.util.httpError
import com.example.newschallenge.news.data.remote.ArticleImage
import com.example.newschallenge.news.data.remote.NewsNineResponse
import com.example.newschallenge.news.data.remote.NewsNineService
import com.example.newschallenge.news.data.local.NewsNineArticlesDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import com.example.newschallenge.news.presentations.ViewState

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface NewsNineRepository {

    /**
     * Gets tne cached news article from database and tries to get
     * fresh news articles from web and save into database
     * if that fails then continues showing cached data.
     */
    fun getNewsArticles(): Flow<ViewState<List<NewsNineArticleDb>>>

    /**
     * Gets fresh news from web.
     */
    suspend fun getNewsFromWebservice(): Response<NewsNineResponse>
}

@Singleton
class DefaultNewsNineRepository @Inject constructor(
    private val newsDao: NewsNineArticlesDao,
    private val newsService: NewsNineService
) : NewsNineRepository, NewsNineMapper {

    override fun getNewsArticles(): Flow<ViewState<List<NewsNineArticleDb>>> = flow {
        // 1. Start with loading
        emit(ViewState.loading())

        // 2. Try to fetch fresh news from web + cache if any
        val freshNews = getNewsFromWebservice()
        // sort list by timeStamp descendingly to display latest article first in the list
        freshNews.body()?.assets?.sortedByDescending { it.timeStamp }?.map {

            val imageList: List<ArticleImage>? = it.relatedImages

            // calculate each image size by multiply width with height
            imageList?.forEach { item ->
                val width = item.width
                val height = item.height
                item.imageSize = width * height
            }
            // sorted list by imageSize ascendingly to display the smallest image first in the list
            imageList?.sortedBy { imageItem -> imageItem.imageSize }
            // get the smallest image at first index and set it into variable "smallestImage" in each NewsNineArticle
            if(!imageList.isNullOrEmpty()){
                it.smallestImage = imageList?.first()?.url
            }
            it
            //save the list into database
        }?.toStorage()?.let(newsDao::clearAndCacheArticles)

        // 3. Get news from cache [cache is always source of truth], which saved the list sorted by ascending and each item has its smallestImage
        val cachedNews = newsDao.getNewsArticles()
        emitAll(cachedNews.map { ViewState.success(it) })
    }
        .flowOn(Dispatchers.IO)

    override suspend fun getNewsFromWebservice(): Response<NewsNineResponse> {
        return try {
            newsService.getTopHeadlines()
        } catch (e: Exception) {
            httpError(404)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface NewsNineRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultNewsNineRepository): NewsNineRepository
}