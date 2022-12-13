package com.example.newschallenge.news

import com.example.newschallenge.core.mapper.Mapper
import com.example.newschallenge.news.api.NewsNineArticle
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb

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