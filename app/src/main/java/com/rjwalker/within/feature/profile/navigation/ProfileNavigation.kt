package com.rjwalker.within.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val PROFILE_ROUTE = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions) = navigate(PROFILE_ROUTE, navOptions)