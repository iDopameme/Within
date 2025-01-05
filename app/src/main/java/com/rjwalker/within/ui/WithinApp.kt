package com.rjwalker.within.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.rjwalker.within.R
import com.rjwalker.within.design.components.WithinBackground
import com.rjwalker.within.design.components.WithinNavigationBar
import com.rjwalker.within.design.components.WithinNavigationBarItem
import com.rjwalker.within.design.components.WithinTopAppBar
import com.rjwalker.within.design.icons.WithinIcons
import com.rjwalker.within.feature.settings.SettingsDialog
import com.rjwalker.within.navigation.WithinNavHost
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@Composable
fun WithinApp(
    appState: WithinAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

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
            showSettingsDialog = showSettingsDialog,
            onSettingsDismissed = { showSettingsDialog = false },
            onSettingsActionClick = { showSettingsDialog = true },
            modifier = modifier,
            windowAdaptiveInfo = windowAdaptiveInfo
        )
    }
}


@Composable
internal fun WithinApp(
    appState: WithinAppState,
    snackBarHostState: SnackbarHostState,
    showSettingsDialog: Boolean,
    onSettingsDismissed: () -> Unit,
    onSettingsActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val currentDestination = appState.currentDestination
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (showSettingsDialog) SettingsDialog(onDismiss = { onSettingsDismissed() })

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") }, selected = false, onClick = { /*TODO*/ }
                )
            }
        }
    ) {

        val destination = appState.currentTopLevelDestination

        Scaffold(
            topBar = {
                WithinTopAppBar(
                    titleRes = R.string.app_name,
                    navigationIcon = WithinIcons.Profile,
                    navigationIconContentDescription = "Profile",
                    actionIcon = WithinIcons.Settings,
                    actionIconContentDescription = "Settings",
                    onNavigationClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    onActionClick = { onSettingsActionClick() }
                )
            },
            bottomBar = {
                WithinNavigationBar{
                    appState.topLevelDestinations.forEach { destination ->
                        val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                        WithinNavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = destination.unselectedIcon,
                                    contentDescription = null
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    imageVector = destination.selectedIcon,
                                    contentDescription = null
                                )
                            },
                            label = { Text(stringResource(destination.titleText)) },
                            selected = selected,
                            onClick = { appState.navigateToTopLevelDestination(destination) }
                        )
                    }
                }
            },
//            floatingActionButton = ,
//            floatingActionButtonPosition =
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                WithinNavHost(
                    withingAppState = appState,
                )
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false
