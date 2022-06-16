package com.vkondrav.ram.app.common.appbar

import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vkondrav.ram.app.common.navigation.title
import com.vkondrav.ram.app.common.utils.TextResource
import com.vkondrav.ram.app.design.ThemeState
import kotlinx.coroutines.launch

@Composable
fun CustomAppBar(
    navController: NavController,
    themeState: ThemeState,
    drawerState: DrawerState,
) {
    val backStackEntryState = navController
        .currentBackStackEntryAsState()
        .let { state ->

            val title = state.value?.arguments?.title ?: TextResource.Literal("")

            BackStackEntryState(
                showBackButton = navController.backQueue.size > 2,
                title = title.string(),
            )
        }

    TopAppBar(
        title = {
            Text(
                text = backStackEntryState.title,
            )
        },
        navigationIcon = {
            if (backStackEntryState.showBackButton) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            } else {
                val coroutineScope = rememberCoroutineScope()
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                ) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    themeState.toggleTheme()
                },
            ) {
                val icon = when (themeState.isThemeDark.value) {
                    true -> Icons.Default.LightMode
                    false -> Icons.Default.DarkMode
                }
                Icon(icon, null)
            }
        },
    )
}

private data class BackStackEntryState(
    val showBackButton: Boolean,
    val title: String,
)
