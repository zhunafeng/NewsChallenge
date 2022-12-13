package com.example.newschallenge.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newschallenge.core.ui.ViewState
import com.example.newschallenge.news.domain.NewsNineRepository
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A container for [NewsNineArticleDb] related data to show on the UI.
 */
@HiltViewModel
class NewsNineArticleViewModel @Inject constructor(
    newsRepository: NewsNineRepository
) : ViewModel() {

    private val newsArticleDb: LiveData<ViewState<List<NewsNineArticleDb>>> =
        newsRepository.getNewsArticles().asLiveData()

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<List<NewsNineArticleDb>>> = newsArticleDb
}