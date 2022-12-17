package com.example.newschallenge.news.presentations.home

import com.example.newschallenge.news.data.local.NewsNineArticleDb


/**
 * Describes all the events originated from
 * [OnItemClickListener].
 */
interface OnItemClickListener {

    /* Describes item click event  */
    fun onItemClicked(newsArticle: NewsNineArticleDb?)
}