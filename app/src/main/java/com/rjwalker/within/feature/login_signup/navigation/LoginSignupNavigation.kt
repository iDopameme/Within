package com.rjwalker.within.feature.login_signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rjwalker.within.feature.login_signup.LoginSignUpRoute

const val LOGIN_SIGNUP_ROUTE = "login_signup_route"

fun NavController.navigateToLogin(navOptions: NavOptions) = navigate(LOGIN_SIGNUP_ROUTE, navOptions)

fun NavGraphBuilder.loginSignupScreen() {
    composable(
        route = LOGIN_SIGNUP_ROUTE,
    ) {
        LoginSignUpRoute()
    }
}