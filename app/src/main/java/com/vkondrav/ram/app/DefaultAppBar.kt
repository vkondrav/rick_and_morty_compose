package com.vkondrav.ram.app

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
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.drawer.usecase.OpenDrawerUseCase
import com.vkondrav.ram.navigation.data.AppBarState
import com.vkondrav.ram.navigation.usecase.FetchAppBarStateUseCase
import com.vkondrav.ram.navigation.usecase.NavigateUpUseCase
import com.vkondrav.ram.theme.controller.usecase.ToggleThemeUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get

@Composable
fun DefaultAppBar(
    navHostController: NavController,
    isThemeDark: State<Boolean>,
    fetchAppBarStateUseCase: FetchAppBarStateUseCase = get(),
    navigateUpUseCase: NavigateUpUseCase = get(),
    openDrawerUseCase: OpenDrawerUseCase = get(),
    toggleThemeUseCase: ToggleThemeUseCase = get(),
) {
    val appBarState = fetchAppBarStateUseCase(navHostController).collectAsState()

    TopAppBar(
        title = {
            Text(
                text = appBarState.value.title.string(),
            )
        },
        navigationIcon = {
            if (appBarState.value.showBackButton) {
                IconButton(
                    onClick = {
                        navigateUpUseCase()
                    },
                ) {
                    Icon(Icons.Default.ArrowBack, null)
                }
            } else {
                IconButton(
                    onClick = {
                        openDrawerUseCase()
                    },
                ) {
                    Icon(Icons.Default.Menu, null)
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    toggleThemeUseCase()
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

@Composable
private fun Flow<AppBarState>.collectAsState() = collectAsState(
    initial = AppBarState(
        showBackButton = false,
        title = TextResource.Literal(""),
    ),
)
