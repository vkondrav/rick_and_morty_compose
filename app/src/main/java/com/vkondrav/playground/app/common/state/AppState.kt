package com.vkondrav.playground.app.common.state

import android.util.Log
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface AppState {
    fun openDrawer()
    fun closeDrawer()
    fun showSnackbar(message: String)
    fun navigate(route: String)
    fun navigateBack()
    fun openBottomSheet()
}

internal class AppStateImpl(
    private val navController: NavController,
    private val snackbarHostState: SnackbarHostState,
    private val drawerState: DrawerState,
    private val bottomSheetScaffoldState: BottomSheetScaffoldState,
    private val coroutineScope: CoroutineScope,
) : AppState {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("AppState", throwable.message, throwable)
    }

    override fun openDrawer() {
        launch { drawerState.open() }
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

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun openBottomSheet() {
        launch { bottomSheetScaffoldState.bottomSheetState.expand() }
    }

    private fun launch(block: suspend CoroutineScope.() -> Unit) =
        coroutineScope.launch(block = block, context = exceptionHandler)
}