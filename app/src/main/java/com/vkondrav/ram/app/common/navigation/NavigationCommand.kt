package com.vkondrav.ram.app.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vkondrav.ram.app.common.utils.TextResource
import kotlinx.coroutines.flow.Flow

sealed class NavigationCommand {
    object NavigateUp : NavigationCommand()
    data class NavigateToRoute(val route: String) : NavigationCommand()
}

data class BackStackEntry(
    val showBackButton: Boolean,
    val title: TextResource,
)

@Composable
fun Flow<BackStackEntry>.collectAsState() = collectAsState(
    initial = BackStackEntry(
        showBackButton = false,
        title = TextResource.Literal(""),
    ),
)
