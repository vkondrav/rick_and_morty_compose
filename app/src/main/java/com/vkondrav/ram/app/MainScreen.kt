package com.vkondrav.ram.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vkondrav.ram.common.ui.screen.BottomSheet
import com.vkondrav.ram.snackbar.SnackbarHost
import com.vkondrav.ram.common.ui.design.DlsTheme
import com.vkondrav.ram.common.ui.design.dlsDarkColorPalette
import com.vkondrav.ram.common.ui.design.dlsLightColorPalette
import com.vkondrav.ram.drawer.view.CustomDrawer
import com.vkondrav.ram.theme.controller.usecase.FetchThemeStateUseCase
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.navigation.defineGraph
import com.vkondrav.ram.navigation.usecase.HandleNavigationCommandsUseCase
import org.koin.androidx.compose.get

@Composable
fun MainScreen(
    handleNavigationCommandsUseCase: HandleNavigationCommandsUseCase = get(),
    fetchThemeStateUseCase: FetchThemeStateUseCase = get(),
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isThemeDark = fetchThemeStateUseCase(
        initialSystemSetting = isSystemInDarkTheme,
    ).collectAsState(initial = isSystemInDarkTheme)

    val navHostController = rememberAnimatedNavController()

    LaunchedEffect("main_screen") {
        handleNavigationCommandsUseCase(navHostController)
    }

    DlsTheme(
        colors = when (isThemeDark.value) {
            true -> dlsDarkColorPalette
            false -> dlsLightColorPalette
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CustomDrawer {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    BottomSheet(
                        title = TextResource.Resource(R.string.favorites),
                        isThemeDark = isThemeDark,
                        tabs = tabs,
                    ) {
                        Column {
                            DefaultAppBar(
                                navHostController,
                                isThemeDark,
                            )
                            AnimatedNavHost(
                                navController = navHostController,
                                startDestination = screens.first().route,
                            ) {
                                defineGraph(screens)
                            }
                        }
                    }
                    SnackbarHost()
                }
            }
        }
    }
}
