package com.rjwalker.within.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
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

@Composable
fun WithinApp(
    appState: WithinAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {

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
        appState, modifier
    )
}


@Composable
internal fun WithinApp(
    appState: WithinAppState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    val currentDestination = appState.currentDestination

    Scaffold { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val destination = appState.currentTopLevelDestination

        }
    }
}
