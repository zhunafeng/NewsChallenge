package com.example.newschallenge.news.data

import com.example.newschallenge.news.data.remote.NewsNineArticle
import com.example.newschallenge.news.data.local.NewsNineArticleDb

interface NewsNineMapper : Mapper<NewsNineArticleDb, NewsNineArticle> {
    override fun NewsNineArticleDb.toRemote(): NewsNineArticle {
        return NewsNineArticle(
            id = news_id,
            url = url,
            headline = headline,
            theAbstract = theAbstract,
            byLine = byLine,
            smallestImage = smallestImage,
            timeStamp = timeStamp
        )
    }

    override fun NewsNineArticle.toStorage(): NewsNineArticleDb {
        return NewsNineArticleDb(
            news_id = id,
            url = url,
            headline = headline,
            theAbstract = theAbstract,
            byLine = byLine,
            smallestImage = smallestImage,
            timeStamp = timeStamp
        )
    }
}