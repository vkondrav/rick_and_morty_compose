package com.vkondrav.playground.app.common.appbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CustomAppBar(navController: NavController) {

    val backStackEntryState = navController
        .currentBackStackEntryAsState()
        .let {
            BackStackEntryState(
                showBackButton = navController.backQueue.size > 2,
                title = it.value?.destination?.route ?: ""
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
                    onClick = { navController.popBackStack() }
                ) { Icon(Icons.Default.ArrowBack, "Back") }
            } else {
                IconButton(onClick = { }) {
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