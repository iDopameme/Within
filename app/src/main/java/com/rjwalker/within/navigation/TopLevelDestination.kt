package com.rjwalker.within.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.rjwalker.within.R
import com.rjwalker.within.design.icons.WithinIcons
import com.rjwalker.within.feature.home.navigation.HomeBaseRoute
import com.rjwalker.within.feature.home.navigation.HomeRoute
import com.rjwalker.within.feature.journal.navigation.JournalRoute
import com.rjwalker.within.feature.tasks.navigation.TasksRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleText: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route
) {
    HOME(
        titleText = R.string.home,
        selectedIcon = WithinIcons.HomeFilled,
        unselectedIcon = WithinIcons.Home,
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class
    ),
    TASKS(
        titleText = R.string.tasks,
        selectedIcon = WithinIcons.TasksFilled,
        unselectedIcon = WithinIcons.Tasks,
        route = TasksRoute::class
    ),
    JOURNAL(
        titleText = R.string.journal,
        selectedIcon = WithinIcons.JournalFilled,
        unselectedIcon = WithinIcons.Journal,
        route = JournalRoute::class
    )
}