package com.rjwalker.within

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WithinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}