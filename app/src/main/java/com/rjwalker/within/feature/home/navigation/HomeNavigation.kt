package com.rjwalker.within.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rjwalker.within.feature.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable data object HomeRoute

@Serializable data object HomeBaseRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeBaseRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    onMenuItemClick: (String) -> Unit = {}
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute>{
            HomeScreen()
        }
    }
}