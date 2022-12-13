package com.example.newschallenge

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class NewsNineApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Plant timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}