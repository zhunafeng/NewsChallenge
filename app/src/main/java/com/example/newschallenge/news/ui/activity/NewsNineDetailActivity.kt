package com.example.newschallenge.news.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.core.app.NavUtils
import com.example.newschallenge.core.ui.base.BaseActivity
import com.example.newschallenge.databinding.ActivityNewsDetailBinding

class NewsNineDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        binding.newsWebview.settings.javaScriptEnabled = true
        binding.newsWebview.settings.domStorageEnabled = true
        binding.newsWebview.settings.loadWithOverviewMode = true
        binding.newsWebview.settings.useWideViewPort = true
        binding.newsWebview.settings.builtInZoomControls = true
        binding.newsWebview.settings.displayZoomControls = false

        binding.newsWebview.webViewClient = WebViewClient()

        setContentView(binding.root)

        val url = intent.getStringExtra("url")
        url?.let { binding.newsWebview.loadUrl(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}