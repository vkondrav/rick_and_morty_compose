package com.vkondrav.ram.app.screen.drawer.usecase

import androidx.compose.material.DrawerState
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class NavigateToRouteUseCase(
    private val navController: NavController,
    private val drawerState: DrawerState,
    private val dispatcher: CoroutineDispatcher,
): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(route: String) {
        navController.navigate(route)
        launch {
            drawerState.close()
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
