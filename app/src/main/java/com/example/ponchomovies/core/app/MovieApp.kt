package com.example.ponchomovies.core.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class MovieApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}