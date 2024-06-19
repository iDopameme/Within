package com.rjwalker.within.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.rjwalker.within.feature.home.navigation.homeScreen
import com.rjwalker.within.feature.login_signup.navigation.LOGIN_SIGNUP_ROUTE
import com.rjwalker.within.feature.login_signup.navigation.loginSignupScreen
import com.rjwalker.within.feature.profile.navigation.profileScreen
import com.rjwalker.within.ui.WithinAppState

@Composable
fun WithinNavHost(
    withingAppState: WithinAppState,
    modifier: Modifier = Modifier,
    startDestination: String = LOGIN_SIGNUP_ROUTE,
) {
    val navController = withingAppState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginSignupScreen()
        homeScreen()
        profileScreen()
    }
}