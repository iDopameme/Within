package com.rjwalker.within.feature.journal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rjwalker.within.feature.journal.JournalScreen

const val JOURNAL_ROUTE = "journal_route"

fun NavController.navigateToJournal(navOptions: NavOptions) = navigate(JOURNAL_ROUTE, navOptions)

fun NavGraphBuilder.journalScreen() {
    composable(
        route = JOURNAL_ROUTE
    ) {
        JournalScreen()
    }
}