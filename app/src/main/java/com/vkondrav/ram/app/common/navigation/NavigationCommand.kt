package com.vkondrav.ram.app.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vkondrav.ram.common.ui.TextResource
import kotlinx.coroutines.flow.Flow

sealed class NavigationCommand {
    object NavigateUp : NavigationCommand()
    data class NavigateToRoute(val route: String) : NavigationCommand()
}

data class AppBarState(
    val showBackButton: Boolean,
    val title: TextResource,
)

@Composable
fun Flow<AppBarState>.collectAsState() = collectAsState(
    initial = AppBarState(
        showBackButton = false,
        title = TextResource.Literal(""),
    ),
)
