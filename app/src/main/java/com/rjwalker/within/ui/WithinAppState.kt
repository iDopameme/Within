package com.rjwalker.within.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.rjwalker.within.data.util.NetworkMonitor
import com.rjwalker.within.data.util.TimeZoneMonitor
import com.rjwalker.within.feature.home.navigation.HOME_ROUTE
import com.rjwalker.within.feature.home.navigation.navigateToHome
import com.rjwalker.within.feature.login_signup.navigation.LOGIN_SIGNUP_ROUTE
import com.rjwalker.within.feature.login_signup.navigation.navigateToLogin
import com.rjwalker.within.feature.profile.navigation.PROFILE_ROUTE
import com.rjwalker.within.feature.profile.navigation.navigateToProfile
import com.rjwalker.within.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone

class WithinAppState (
    val navController: NavController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> TopLevelDestination.HOME
            PROFILE_ROUTE -> TopLevelDestination.PROFILE
            LOGIN_SIGNUP_ROUTE -> TopLevelDestination.LOGIN
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val currentTimeZone = timeZoneMonitor.currentTimeZone
        .stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            TimeZone.currentSystemDefault()
        )

    fun navigateToTopLevelDestination(toplevelDestination: TopLevelDestination) {
        trace("Navigation: ${toplevelDestination.name}") {
            val topLevelDestination = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true

                restoreState = true
            }

            when (toplevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelDestination)
                TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelDestination)
                TopLevelDestination.LOGIN -> navController.navigateToLogin(topLevelDestination)
            }
        }
    }
}