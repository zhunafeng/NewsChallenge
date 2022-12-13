package com.example.newschallenge.utils.storage

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newschallenge.news.storage.NewsNineDatabase
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb
import com.example.newschallenge.utils.DaoTest
import com.example.newschallenge.utils.assertItems
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsArticlesDaoTest : DaoTest<NewsNineDatabase>(NewsNineDatabase::class.java) {

    @Test
    @Throws(InterruptedException::class)
    fun insertNewsArticles() {
        // GIVEN
        val input = listOf(NewsNineArticleDb(1), NewsNineArticleDb(2))

        // THEN
        assertThat(db.newsArticlesDao().insertArticles(input), equalTo(listOf(1L, 2L)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertNewsArticlesAndRead(): Unit = runBlocking {
        // GIVEN
        val input = listOf(
            NewsNineArticleDb(1, 11, "Hello"),
            NewsNineArticleDb(2, 22, "Testing")
        )
        db.newsArticlesDao().insertArticles(input)

        // THEN
        db.newsArticlesDao().getNewsArticles().assertItems(input)
    }
}