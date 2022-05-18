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
import com.vkondrav.playground.app.common.nav.title
import com.vkondrav.playground.app.common.state.AppState
import org.koin.androidx.compose.get

@Composable
fun CustomAppBar(navController: NavController) {

    val appState: AppState = get()
    val backStackEntryState = navController
        .currentBackStackEntryAsState()
        .let { state ->

            val title = state.value?.arguments?.title ?: ""

            BackStackEntryState(
                showBackButton = navController.backQueue.size > 2,
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
                    onClick = {
                        appState.navigateBack()
                    }
                ) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            } else {
                IconButton(onClick = {
                    appState.openDrawer()
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