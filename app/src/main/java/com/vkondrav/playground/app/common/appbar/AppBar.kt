package com.vkondrav.playground.app.common.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vkondrav.playground.app.common.action.NavigateBackAction
import com.vkondrav.playground.app.common.action.OpenDrawerAction
import com.vkondrav.playground.app.common.navigation.Route
import com.vkondrav.playground.app.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomAppBar() {
    val viewModel = getViewModel<DrawerViewModel>()

    val backStackEntryState = viewModel.navController
        .currentBackStackEntryAsState()
        .let { state ->

            val title = Route.allScreens.find { screen ->
                screen.id == state.value?.destination?.route
            }?.title ?: ""

            BackStackEntryState(
                showBackButton = viewModel.navController.backQueue.size > 2,
                title = title
            )
        }

    TopAppBar(
        title = {
            Text(
                text = backStackEntryState.title
            )
        },
        navigationIcon = {
            if (backStackEntryState.showBackButton) {
                IconButton(
                    onClick = { viewModel.onAction(NavigateBackAction) }
                ) { Icon(Icons.Default.ArrowBack, "Back") }
            } else {
                IconButton(onClick = {
                    viewModel.onAction(OpenDrawerAction)
                }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            }
        }
    )
}

private data class BackStackEntryState(
    val showBackButton: Boolean,
    val title: String,
)