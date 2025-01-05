package com.rjwalker.within.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjwalker.within.feature.home.navigation.HomeBaseRoute
import com.rjwalker.within.feature.home.navigation.homeScreen
import com.rjwalker.within.feature.journal.navigation.journalScreen
import com.rjwalker.within.feature.profile.ProfileScreen
import com.rjwalker.within.ui.WithinAppState

@Composable
fun WithinNavHost(
    withingAppState: WithinAppState,
    modifier: Modifier = Modifier,
) {
    val navController = withingAppState.navController
    NavHost(
        navController = navController,
        startDestination = HomeBaseRoute,
        modifier = modifier
    ) {
        homeScreen()
//        tasksScreen()
        journalScreen()
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}