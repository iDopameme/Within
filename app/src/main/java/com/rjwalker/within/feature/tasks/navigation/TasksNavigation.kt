package com.rjwalker.within.feature.tasks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rjwalker.within.feature.tasks.TasksScreen

const val TASKS_ROUTE = "tasks_route"

fun NavController.navigateToTasks(navOptions: NavOptions) = navigate(TASKS_ROUTE, navOptions)

fun NavGraphBuilder.tasksScreen() {
    composable(
        route = TASKS_ROUTE
    ) {
        TasksScreen()
    }
}