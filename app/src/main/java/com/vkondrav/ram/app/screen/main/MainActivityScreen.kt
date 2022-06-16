package com.vkondrav.ram.app.screen.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vkondrav.ram.app.common.appbar.CustomAppBar
import com.vkondrav.ram.app.common.bottom_sheet.BottomSheet
import com.vkondrav.ram.app.common.navigation.defineGraph
import com.vkondrav.ram.app.common.state.LoadAppStateIntoKoin
import com.vkondrav.ram.app.design.DlsTheme
import com.vkondrav.ram.app.design.ThemeState
import com.vkondrav.ram.app.design.dlsDarkColorPalette
import com.vkondrav.ram.app.design.dlsLightColorPalette
import com.vkondrav.ram.app.screen.characters.nav.charactersScreen
import com.vkondrav.ram.app.screen.drawer.composable.CustomDrawer
import com.vkondrav.ram.app.snackbar.SnackbarHost

@Composable
fun MainActivityScreen() {
    val navController = rememberAnimatedNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetState = rememberBottomSheetScaffoldState()

    val isThemeDark = isSystemInDarkTheme()
    val themeState = remember { ThemeState(isThemeDark) }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )

    LoadAppStateIntoKoin(
        navController,
        snackbarHostState,
        drawerState,
    )

    DlsTheme(
        colors = when (themeState.isThemeDark.value) {
            true -> dlsDarkColorPalette()
            false -> dlsLightColorPalette()
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CustomDrawer(drawerState) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BottomSheet(bottomSheetState, themeState) {
                        Column {
                            CustomAppBar(navController, themeState, drawerState)
                            AnimatedNavHost(
                                navController = navController,
                                startDestination = charactersScreen.route,
                            ) {
                                defineGraph()
                            }
                        }
                    }
                    SnackbarHost(snackbarHostState)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainActivityScreen()
}
