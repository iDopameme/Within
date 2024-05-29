package com.rjwalker.within.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rjwalker.within.feature.home.HomeScreen
import com.rjwalker.within.feature.profile.ProfileScreen
import com.rjwalker.within.feature.settings.SettingsScreen

@Composable
fun WithinNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
    }
}