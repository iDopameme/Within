package com.rjwalker.within.feature.login_signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val LOGIN_SIGNUP_ROUTE = "login_signup_route"

fun NavController.navigateToLogin(navOptions: NavOptions) = navigate(LOGIN_SIGNUP_ROUTE, navOptions)

