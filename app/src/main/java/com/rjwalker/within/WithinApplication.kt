package com.rjwalker.within

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import dagger.hilt.android.HiltAndroidApp
import java.util.prefs.Preferences

@HiltAndroidApp
class WithinApplication : Application() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "within_preferences")

    override fun onCreate() {
        super.onCreate()
    }
}