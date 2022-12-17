package com.example.newschallenge.utils

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.newschallenge.news.presentations.home.NewsNineActivity

@RunWith(AndroidJUnit4::class)
class OpenUrlInstrumentedTest {

    @get:Rule
    lateinit var activityScenarioRule: ActivityScenario<NewsNineActivity>


    @Before
    fun start(){
        activityScenarioRule = ActivityScenario.launch(startIntent)
    }

    @Test
    fun givenWhenClickThenGoingToDetailActivity(){

    }

    companion object {

        val STRING_TO_BE_TYPED = "Espresso"

        lateinit var startIntent: Intent
        @BeforeClass
        @JvmStatic
        fun setup(){
            startIntent = Intent(ApplicationProvider.getApplicationContext(),NewsNineActivity::class.java)
            startIntent.putExtra(STRING_TO_BE_TYPED,"url")
        }
    }



}