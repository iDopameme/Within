package com.rjwalker.within.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_ROUTE, navOptions)