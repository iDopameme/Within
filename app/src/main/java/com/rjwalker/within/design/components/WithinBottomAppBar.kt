package com.rjwalker.within.design.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rjwalker.within.design.icons.WithinIcons
import com.rjwalker.within.design.theme.WithinTheme

@Composable
fun RowScope.WithinNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
    )
}

@Composable
fun WithinNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = WithinNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun WithinNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = WithinNavigationDefaults.navigationContentColor(),
            selectedTextColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = WithinNavigationDefaults.navigationContentColor(),
            indicatorColor = WithinNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun WithinNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = WithinNavigationDefaults.navigationContentColor(),
        header = header,
        content = content
    )
}

@Composable
fun WithinNavigationSuiteScaffold(
    navigationSuiteItems: WithinNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = WithinNavigationDefaults.navigationContentColor(),
            selectedTextColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = WithinNavigationDefaults.navigationContentColor(),
            indicatorColor = WithinNavigationDefaults.navigationIndicatorColor()
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = WithinNavigationDefaults.navigationContentColor(),
            selectedTextColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = WithinNavigationDefaults.navigationContentColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = WithinNavigationDefaults.navigationContentColor(),
            selectedTextColor = WithinNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = WithinNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            WithinNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = WithinNavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

//@ThemePreviews
//@Composable


class WithinNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier
    )
}

@ThemePreviews
@Composable
fun WithinNavigationBarPreview() {
    val items = listOf("Home", "Build", "Community")
    val icons = listOf(
        WithinIcons.Home,
        WithinIcons.Fire,
        WithinIcons.Friends
    )
    val selectedIcons = listOf(
        WithinIcons.HomeFilled,
        WithinIcons.FireFilled,
        WithinIcons.FriendsFilled
    )

    WithinTheme {
        WithinNavigationBar {
            items.forEachIndexed { index, item ->
                WithinNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun WithinNavigationRailPreview() {
    val items = listOf("Home", "Build", "Community")
    val icons = listOf(
        WithinIcons.Home,
        WithinIcons.Fire,
        WithinIcons.Friends
    )
    val selectedIcons = listOf(
        WithinIcons.HomeFilled,
        WithinIcons.FireFilled,
        WithinIcons.FriendsFilled
    )

    WithinTheme {
        WithinNavigationRail{
            items.forEachIndexed { index, item ->
                WithinNavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { }
                )
            }
        }
    }
}



object WithinNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}