package com.vkondrav.ram.app.screen.main

import androidx.navigation.NavController
import com.vkondrav.ram.app.base.viewmodel.BaseViewModel
import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.app.common.state.DrawerStateHolder
import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.design.ThemeState
import kotlinx.coroutines.CoroutineDispatcher

class MainActivityViewModel(
    private val themeState: ThemeState,
    private val drawerStateHolder: DrawerStateHolder,
    private val navigator: Navigator,
    snackbarMessageStateHolder: SnackbarMessageStateHolder,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher) {

    val snackbarMessage = snackbarMessageStateHolder.state

    val drawerState = drawerStateHolder.isOpen

    val isDarkTheme = themeState.isDarkTheme

    suspend fun handleNavigationCommands(navController: NavController) {
        navigator.handleNavigationCommands(navController)
    }

    fun backStackState(navController: NavController) =
        navigator.backStackState(navController)

    fun openDrawer() {
        drawerStateHolder.open()
    }

    fun closeDrawer() {
        drawerStateHolder.close()
    }

    fun toggleTheme() {
        themeState.toggleTheme()
    }

    fun navigateUp() {
        navigator.navigateUp()
    }
}
