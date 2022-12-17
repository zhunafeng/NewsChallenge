package com.example.newschallenge.news.data.local

import android.app.Application
import com.example.newschallenge.news.data.local.NewsNineArticlesDao
import com.example.newschallenge.news.data.local.NewsNineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsNineDatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): NewsNineDatabase = NewsNineDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideUserDao(db: NewsNineDatabase): NewsNineArticlesDao = db.newsArticlesDao()
}