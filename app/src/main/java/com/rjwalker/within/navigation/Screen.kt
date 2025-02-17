package com.rjwalker.within.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Profile: Screen("profile")
    data object Tasks: Screen("tasks")
    data object Journal: Screen("journal")
}