package com.example.newschallenge.news.di

import com.example.newschallenge.news.api.NewsNineService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsNineServiceModule {

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsNineService = retrofit.create(NewsNineService::class.java)
}