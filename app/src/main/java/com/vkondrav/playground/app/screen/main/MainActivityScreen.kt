package com.vkondrav.playground.app.screen.main

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
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.bottom_sheet.composable.BottomSheet
import com.vkondrav.playground.app.common.navigation.defineGraph
import com.vkondrav.playground.app.common.state.LoadAppStateIntoKoin
import com.vkondrav.playground.app.design.DlsTheme
import com.vkondrav.playground.app.screen.characters.nav.charactersScreen
import com.vkondrav.playground.app.screen.drawer.composable.CustomDrawer
import com.vkondrav.playground.app.snackbar.SnackbarHost

@Composable
fun MainActivityScreen() {
    val navController = rememberAnimatedNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetState = rememberBottomSheetScaffoldState()

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )

    LoadAppStateIntoKoin(
        navController,
        snackbarHostState,
        drawerState,
        bottomSheetState,
    )

    DlsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DlsTheme.colors.background,
        ) {
            CustomDrawer(drawerState) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BottomSheet(bottomSheetState) {
                        Column {
                            CustomAppBar(navController)
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
