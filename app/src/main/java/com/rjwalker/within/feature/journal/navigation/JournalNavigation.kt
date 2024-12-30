package com.rjwalker.within.feature.journal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rjwalker.within.feature.journal.JournalScreen
import kotlinx.serialization.Serializable

@Serializable data object JournalRoute

fun NavController.navigateToJournal(navOptions: NavOptions) = navigate(route = JournalRoute, navOptions)

fun NavGraphBuilder.journalScreen() {
    composable<JournalRoute>{
        JournalScreen()
    }
}