package com.example.newschallenge.news.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb
import kotlinx.coroutines.flow.Flow

/**
 * Defines access layer to news articles table
 */
@Dao
interface NewsNineArticlesDao {

    /**
     * Insert articles into the table
     */
    @Insert
    fun insertArticles(articles: List<NewsNineArticleDb>): List<Long>

    @Query("DELETE FROM news_nine_article")
    fun clearAllArticles()

    @Transaction
    fun clearAndCacheArticles(articles: List<NewsNineArticleDb>) {
        clearAllArticles()
        insertArticles(articles)
    }

    /**
     * Get all the articles from table
     */
    @Query("SELECT * FROM news_nine_article")
    fun getNewsArticles(): Flow<List<NewsNineArticleDb>>
}