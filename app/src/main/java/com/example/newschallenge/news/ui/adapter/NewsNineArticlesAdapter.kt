package com.example.newschallenge.news.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.request.CachePolicy
import com.example.newschallenge.R
import com.example.newschallenge.core.utils.inflate
import com.example.newschallenge.databinding.RowNewsArticleBinding
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb
import com.example.newschallenge.news.ui.model.OnItemClickListener

/**
 * The News adapter to show the news in a list.
 */
class NewsNineArticlesAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<NewsNineArticleDb, NewsNineArticlesAdapter.NewsNineHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsNineHolder(parent.inflate(R.layout.row_news_article))

    override fun onBindViewHolder(newsHolder: NewsNineHolder, position: Int) =
        newsHolder.bind(getItem(position), listener)

    /**
     * View Holder Pattern
     */
    class NewsNineHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RowNewsArticleBinding.bind(itemView)

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: NewsNineArticleDb, listener: OnItemClickListener) =
            with(itemView) {
                binding.newsAbstract.text = newsArticle.theAbstract // news abstract
                binding.newsHeadline.text = newsArticle.headline // headline
                binding.newsByLine.text = newsArticle.byLine // news author


                //handle downloading images into memory cache and disk cache
                val imgLoader = ImageLoader.Builder(context)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build()
                binding.newsImage.load(newsArticle.smallestImage, imgLoader) {
                    placeholder(R.drawable.tools_placeholder)
                    error(R.drawable.tools_placeholder)
                }

                setOnClickListener { listener.onItemClicked(newsArticle) }
            }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsNineArticleDb>() {
            override fun areItemsTheSame(
                oldItem: NewsNineArticleDb,
                newItem: NewsNineArticleDb
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: NewsNineArticleDb,
                newItem: NewsNineArticleDb
            ): Boolean = oldItem == newItem
        }
    }
}