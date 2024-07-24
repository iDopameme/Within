package com.rjwalker.within.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rjwalker.within.R
import com.rjwalker.within.design.components.WelcomeComponent
import com.rjwalker.within.design.components.WithinBackground
import com.rjwalker.within.design.components.WithinNavigationBar
import com.rjwalker.within.design.components.WithinNavigationBarItem
import com.rjwalker.within.design.components.WithinTopAppBar
import com.rjwalker.within.design.icons.WithinIcons

@Composable
fun WithinApp(
    appState: WithinAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {

    WithinBackground(modifier = modifier) {
        val snackBarHostState = remember { SnackbarHostState() }
        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

        val notConnectedSnackBar = stringResource(id = R.string.not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackBarHostState.showSnackbar(
                    message = notConnectedSnackBar,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

        WithinApp(
            appState = appState,
            snackBarHostState = snackBarHostState,
            modifier = modifier,
            windowAdaptiveInfo = windowAdaptiveInfo
        )
    }
}


@Composable
internal fun WithinApp(
    appState: WithinAppState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val currentDestination = appState.currentDestination
    val items = listOf(R.string.home, R.string.tasks, R.string.journal)
    val icons = listOf(
        WithinIcons.Home,
        WithinIcons.Tasks,
        WithinIcons.Journal
    )
    val selectedIcons = listOf(
        WithinIcons.HomeFilled,
        WithinIcons.TasksFilled,
        WithinIcons.JournalFilled
    )

    Scaffold(
        topBar = {
            WithinTopAppBar(
                titleRes = R.string.app_name,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconContentDescription = "Back",
                actionIcon = Icons.Filled.Settings,
                actionIconContentDescription = "Settings"
            )
        },
        bottomBar = {
            WithinNavigationBar{
                items.forEachIndexed { index, item ->
                    WithinNavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = item.toString()
                            )
                        },
                        selectedIcon = {
                            Icon(
                                imageVector = selectedIcons[index],
                                contentDescription = item.toString()
                            )
                        },
                        label = { Text(stringResource(id = item)) },
                        selected = index == 0,
                        onClick = { }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val destination = appState.currentTopLevelDestination


            WelcomeComponent(isUserNew = true)
        }
    }
}
