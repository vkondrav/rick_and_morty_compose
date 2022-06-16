package com.vkondrav.playground.app.common.state

import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

interface AppState {
    fun closeDrawer()
    fun showSnackbar(message: String)
    fun navigate(route: String)
}

internal class AppStateImpl(
    private val navController: NavController,
    private val snackbarHostState: SnackbarHostState,
    private val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope,
) : AppState {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override fun closeDrawer() {
        launch { drawerState.close() }
    }

    override fun showSnackbar(message: String) {
        launch { snackbarHostState.showSnackbar(message) }
    }

    override fun navigate(route: String) {
        navController.navigate(route)
    }

    private fun launch(block: suspend CoroutineScope.() -> Unit) =
        coroutineScope.launch(block = block, context = exceptionHandler)
}
