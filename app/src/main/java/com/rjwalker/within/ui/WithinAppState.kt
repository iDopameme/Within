package com.rjwalker.within.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.rjwalker.within.data.util.NetworkMonitor
import com.rjwalker.within.data.util.TimeZoneMonitor
import com.rjwalker.within.feature.home.navigation.HOME_ROUTE
import com.rjwalker.within.feature.home.navigation.navigateToHome
import com.rjwalker.within.feature.journal.navigation.JOURNAL_ROUTE
import com.rjwalker.within.feature.journal.navigation.navigateToJournal
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


@Composable
fun rememberWithinAppState(
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): WithinAppState {
    return remember(
        navController,
        coroutineScope,
        networkMonitor,
        timeZoneMonitor
    ) {
        WithinAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor,
            timeZoneMonitor = timeZoneMonitor
        )
    }
}

class WithinAppState (
    val navController: NavHostController,
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
            JOURNAL_ROUTE -> TopLevelDestination.JOURNAL
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

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true

                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
                TopLevelDestination.JOURNAL -> navController.navigateToJournal(topLevelNavOptions)
                TopLevelDestination.LOGIN -> navController.navigateToLogin(topLevelNavOptions)
            }
        }
    }
}