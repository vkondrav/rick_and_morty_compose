package com.vkondrav.playground.app.screen.main

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
import com.vkondrav.playground.app.screen.characters.nav.charactersScreen
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.defineGraph
import com.vkondrav.playground.app.screen.drawer.composable.CustomDrawer
import com.vkondrav.playground.app.common.state.LoadAppStateIntoKoin
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
        CustomDrawer(drawerState) {
            Box(modifier = Modifier.background(Color.White)) {
                Column {
                    CustomAppBar(navController)
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = charactersScreen.id,
                    ) {
                        defineGraph()
                    }
                }
                SnackbarHost(snackbarHostState)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainActivityScreen()
}