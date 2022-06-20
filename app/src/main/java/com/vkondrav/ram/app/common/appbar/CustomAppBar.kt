package com.vkondrav.ram.app.common.appbar

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
import androidx.compose.runtime.State
import com.vkondrav.ram.app.common.navigation.BackStackEntry

@Composable
fun CustomAppBar(
    onBackPressed: () -> Unit,
    onOpenDrawer: () -> Unit,
    onToggleTheme: () -> Unit,
    backStackEntry: State<BackStackEntry>,
    isThemeDark: State<Boolean>,
) {
    TopAppBar(
        title = {
            Text(
                text = backStackEntry.value.title.string(),
            )
        },
        navigationIcon = {
            if (backStackEntry.value.showBackButton) {
                IconButton(
                    onClick = {
                        onBackPressed()
                    },
                ) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            } else {
                IconButton(
                    onClick = {
                        onOpenDrawer()
                    },
                ) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onToggleTheme()
                },
            ) {
                val icon = when (isThemeDark.value) {
                    true -> Icons.Default.LightMode
                    false -> Icons.Default.DarkMode
                }
                Icon(icon, null)
            }
        },
    )
}
