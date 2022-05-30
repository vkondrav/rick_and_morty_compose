package com.vkondrav.playground.app.common.state

import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@Composable
fun LoadAppStateIntoKoin(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
) {
    val coroutineScope = rememberCoroutineScope()

    loadKoinModules(module {
        single<AppState> {
            AppStateImpl(
                navController = navController,
                snackbarHostState = snackbarHostState,
                drawerState = drawerState,
                coroutineScope = coroutineScope,
            )
        }
    })
}
