package com.vkondrav.playground.app.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vkondrav.playground.app.bottomsheet.CustomBottomSheet
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.defineGraph
import com.vkondrav.playground.app.drawer.composable.Drawer
import com.vkondrav.playground.app.main.di.LoadAppStateIntoKoin
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.snackbar.SnackbarHost

@Composable
fun MainActivityScreen() {

    val navController = rememberAnimatedNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed),
        drawerState = drawerState,
        snackbarHostState = snackbarHostState,
    )

    LoadAppStateIntoKoin(
        navController,
        snackbarHostState,
        drawerState,
        bottomSheetScaffoldState,
    )

    MaterialTheme {
        Drawer(drawerState) {
            CustomBottomSheet(bottomSheetScaffoldState) {
                Box(modifier = Modifier.background(Color.White)) {
                    Column {
                        CustomAppBar(navController)
                        AnimatedNavHost(
                            navController = navController,
                            startDestination = page1Screen.id,
                        ) {
                            defineGraph()
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