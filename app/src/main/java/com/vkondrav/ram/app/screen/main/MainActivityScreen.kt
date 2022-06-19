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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vkondrav.ram.app.common.appbar.BackStackEntryState
import com.vkondrav.ram.app.common.appbar.CustomAppBar
import com.vkondrav.ram.app.common.bottom_sheet.BottomSheet
import com.vkondrav.ram.app.common.navigation.defineGraph
import com.vkondrav.ram.app.common.navigation.title
import com.vkondrav.ram.app.common.state.Nav
import com.vkondrav.ram.app.common.utils.TextResource
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

    val backStackEntryState = navHostController
        .currentBackStackEntryAsState()
        .let { state ->

            val title = state.value?.arguments?.title ?: TextResource.Literal("")

            BackStackEntryState(
                showBackButton = navHostController.backQueue.size > 2,
                title = title.string(),
            )
        }

    LaunchedEffect(viewModel) {
        viewModel.navigation.collect {
            when (it) {
                is Nav.Route -> navHostController.navigate(it.destination)
                is Nav.Back -> navHostController.popBackStack()
            }
        }
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
                                    viewModel.navigateBack()
                                },
                                onOpenDrawer = {
                                    viewModel.openDrawer()
                                },
                                onToggleTheme = {
                                    viewModel.toggleTheme()
                                },
                                backStackEntryState = backStackEntryState,
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
