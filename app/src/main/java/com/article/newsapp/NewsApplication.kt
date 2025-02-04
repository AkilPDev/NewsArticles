package com.article.newsapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application(){
    init {
        instance = this
    }
    companion object {
        private var instance: NewsApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}