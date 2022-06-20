package com.vkondrav.ram.app.screen.main

import androidx.compose.material.DrawerState
import androidx.navigation.NavController
import com.vkondrav.ram.app.base.viewmodel.BaseViewModel
import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.design.ThemeState
import kotlinx.coroutines.CoroutineDispatcher

class MainActivityViewModel(
    private val themeState: ThemeState,
    private val drawerController: DrawerController,
    private val navigator: Navigator,
    snackbarMessageStateHolder: SnackbarMessageStateHolder,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher) {

    val snackbarMessage = snackbarMessageStateHolder.state

    val isDarkTheme = themeState.isDarkTheme

    suspend fun handleNavigationCommands(navController: NavController) {
        navigator.handleNavigationCommands(navController)
    }

    suspend fun handleDrawerState(drawerState: DrawerState) {
        drawerController.handleDrawerState(drawerState)
    }

    fun appBarState(navController: NavController) =
        navigator.appBarState(navController)

    fun openDrawer() {
        drawerController.open()
    }

    fun toggleTheme() {
        themeState.toggleTheme()
    }

    fun navigateUp() {
        navigator.navigateUp()
    }
}
