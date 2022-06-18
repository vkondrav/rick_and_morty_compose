package com.vkondrav.ram.app.common.state

import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@Composable
fun LoadStateIntoKoin(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()

    loadKoinModules(
        module {
            factory {
                navController
            }
            factory {
                SnackbarHostStateWrapper(
                    snackbarHostState = snackbarHostState,
                    scope = scope,
                )
            }
            factory {
                DrawerStateWrapper(
                    drawerState = drawerState,
                    scope = scope,
                )
            }
        },
    )
}
