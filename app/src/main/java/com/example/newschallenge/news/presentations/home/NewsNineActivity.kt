package com.example.newschallenge.news.presentations.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newschallenge.news.util.observeNotNull
import com.example.newschallenge.news.util.toast
import com.example.newschallenge.databinding.ActivityMainBinding
import com.example.newschallenge.news.data.local.NewsNineArticleDb
import com.example.newschallenge.news.presentations.BaseActivity
import com.example.newschallenge.news.presentations.ViewState
import com.example.newschallenge.news.presentations.detail.NewsNineDetailActivity


class NewsNineActivity : BaseActivity(), OnItemClickListener {

    private val newsArticleViewModel: NewsNineArticleViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    /**
     * Starting point of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up RecyclerView and adapter
        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)

        val adapter = NewsNineArticlesAdapter(this)
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this)

        // Update the UI on state change
        newsArticleViewModel.getNewsArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> adapter.submitList(state.data)
                is ViewState.Loading -> binding.newsList.showLoading()
                is ViewState.Error -> toast("Something went wrong ¯\\_(ツ)_/¯ => ${state.message}")
            }
        }

    }

    //RecyclerView item click listener
    override fun onItemClicked(newsArticle: NewsNineArticleDb?) {
        startActivity(Intent(this, NewsNineDetailActivity::class.java).apply {
            putExtra("url", newsArticle?.url)
        })
    }
}
