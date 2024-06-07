package com.rjwalker.within.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Profile: Screen("profile")
    data object Settings: Screen("settings")
    data object Login: Screen("login")
    data object AI: Screen("ai")
}