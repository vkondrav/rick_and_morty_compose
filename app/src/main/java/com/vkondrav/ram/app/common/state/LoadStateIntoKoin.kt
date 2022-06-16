package com.vkondrav.ram.app.common.state

import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.navigation.NavController
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun loadStateIntoKoin(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
) {
    loadKoinModules(
        module {
            factory {
                navController
            }
            factory {
                snackbarHostState
            }
            factory {
                drawerState
            }
        },
    )
}
