package com.example.newschallenge.news.presentations

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base activity providing Dagger support and [ViewModel] support
 */

typealias BaseActivity = DaggerActivity

@AndroidEntryPoint
abstract class DaggerActivity : AppCompatActivity()