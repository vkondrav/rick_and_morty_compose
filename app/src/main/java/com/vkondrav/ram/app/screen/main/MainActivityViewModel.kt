package com.vkondrav.ram.app.screen.main

import com.vkondrav.ram.app.base.viewmodel.BaseViewModel
import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.app.common.state.DrawerStateHolder
import com.vkondrav.ram.app.common.state.NavigationStateHolder
import com.vkondrav.ram.app.design.ThemeState
import kotlinx.coroutines.CoroutineDispatcher

class MainActivityViewModel(
    private val themeState: ThemeState,
    private val drawerStateHolder: DrawerStateHolder,
    private val navigationStateHolder: NavigationStateHolder,
    snackbarMessageStateHolder: SnackbarMessageStateHolder,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher) {

    val snackbarMessage = snackbarMessageStateHolder.state

    val drawerState = drawerStateHolder.isOpen

    val isDarkTheme = themeState.isDarkTheme

    val navigation = navigationStateHolder.nav

    fun openDrawer() {
        drawerStateHolder.open()
    }

    fun closeDrawer() {
        drawerStateHolder.close()
    }

    fun toggleTheme() {
        themeState.toggleTheme()
    }

    fun navigateBack() {
        navigationStateHolder.navigateBack()
    }
}
