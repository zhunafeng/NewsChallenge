package com.example.newschallenge.news.ui.model

import com.example.newschallenge.news.storage.entity.NewsNineArticleDb


/**
 * Describes all the events originated from
 * [OnItemClickListener].
 */
interface OnItemClickListener {

    /* Describes item click event  */
    fun onItemClicked(newsArticle: NewsNineArticleDb?)
}