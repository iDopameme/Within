package com.rjwalker.within

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rjwalker.within.design.theme.WithinTheme
import com.rjwalker.within.ui.WithinApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WithinTheme {
                WithinApp()
            }
        }
    }
}