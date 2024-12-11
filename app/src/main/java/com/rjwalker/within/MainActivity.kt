package com.rjwalker.within

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rjwalker.within.data.util.LocalTimeZone
import com.rjwalker.within.data.util.NetworkMonitor
import com.rjwalker.within.data.util.TimeZoneMonitor
import com.rjwalker.within.design.theme.WithinTheme
import com.rjwalker.within.ui.WithinApp
import com.rjwalker.within.ui.rememberWithinAppState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var timeZoneMonitor: TimeZoneMonitor

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberWithinAppState(
                networkMonitor = networkMonitor,
                timeZoneMonitor = timeZoneMonitor
            )

            val currentTimeZone by appState.currentTimeZone.collectAsStateWithLifecycle()

            CompositionLocalProvider(
                LocalTimeZone provides currentTimeZone
            ) {
                WithinTheme {
                    WithinApp(appState)
                }
            }
        }
    }
}