package com.vkondrav.playground.app.common.state

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.common.state.AppStateImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@Composable
fun LoadAppStateIntoKoin(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
) {
    val coroutineScope = rememberCoroutineScope()

    loadKoinModules(module {
        single<AppState> {
            AppStateImpl(
                navController = navController,
                snackbarHostState = snackbarHostState,
                drawerState = drawerState,
                bottomSheetScaffoldState = bottomSheetScaffoldState,
                coroutineScope = coroutineScope,
            )
        }
    })
}