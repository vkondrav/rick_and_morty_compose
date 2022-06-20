package com.vkondrav.ram.app.screen.main

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
import com.vkondrav.ram.app.common.appbar.CustomAppBar
import com.vkondrav.ram.app.common.bottom_sheet.BottomSheet
import com.vkondrav.ram.app.common.navigation.collectAsState
import com.vkondrav.ram.app.common.navigation.defineGraph
import com.vkondrav.ram.app.design.DlsTheme
import com.vkondrav.ram.app.design.dlsDarkColorPalette
import com.vkondrav.ram.app.design.dlsLightColorPalette
import com.vkondrav.ram.app.screen.characters.nav.charactersScreen
import com.vkondrav.ram.app.screen.drawer.composable.CustomDrawer
import com.vkondrav.ram.app.snackbar.SnackbarHost
import org.koin.androidx.compose.getViewModel

@Composable
fun MainActivityScreen(
    viewModel: MainActivityViewModel = getViewModel(),
) {
    val isThemeDark = viewModel.isDarkTheme.collectAsState(initial = isSystemInDarkTheme())

    val navHostController = rememberAnimatedNavController()

    LaunchedEffect(viewModel) {
        viewModel.handleNavigationCommands(navHostController)
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
            CustomDrawer(viewModel) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    BottomSheet(
                        isThemeDark = isThemeDark,
                    ) {
                        Column {
                            CustomAppBar(
                                onBackPressed = {
                                    viewModel.navigateUp()
                                },
                                onOpenDrawer = {
                                    viewModel.openDrawer()
                                },
                                onToggleTheme = {
                                    viewModel.toggleTheme()
                                },
                                appBarState = viewModel.appBarState(navHostController)
                                    .collectAsState(),
                                isThemeDark = isThemeDark,
                            )
                            AnimatedNavHost(
                                navController = navHostController,
                                startDestination = charactersScreen.route,
                            ) {
                                defineGraph()
                            }
                        }
                    }
                    SnackbarHost(viewModel.snackbarMessage)
                }
            }
        }
    }
}
