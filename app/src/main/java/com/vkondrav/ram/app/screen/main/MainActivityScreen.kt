package com.vkondrav.ram.app.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.vkondrav.ram.app.common.appbar.CustomAppBar
import com.vkondrav.ram.app.common.bottom_sheet.BottomSheet
import com.vkondrav.ram.app.common.navigation.defineGraph
import com.vkondrav.ram.app.design.DlsTheme
import com.vkondrav.ram.app.design.ThemeState
import com.vkondrav.ram.app.design.dlsDarkColorPalette
import com.vkondrav.ram.app.design.dlsLightColorPalette
import com.vkondrav.ram.app.screen.characters.nav.charactersScreen
import com.vkondrav.ram.app.screen.drawer.composable.CustomDrawer
import com.vkondrav.ram.app.snackbar.SnackbarHost

@Composable
fun MainActivityScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    themeState: ThemeState,
    drawerState: DrawerState,
) {
    DlsTheme(
        colors = when (themeState.isThemeDark.value) {
            true -> dlsDarkColorPalette
            false -> dlsLightColorPalette
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CustomDrawer(drawerState) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    BottomSheet(
                        bottomSheetScaffoldState = bottomSheetScaffoldState,
                        themeState = themeState,
                    ) {
                        Column {
                            CustomAppBar(
                                navController = navHostController,
                                themeState = themeState,
                                drawerState = drawerState,
                            )
                            AnimatedNavHost(
                                navController = navHostController,
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
