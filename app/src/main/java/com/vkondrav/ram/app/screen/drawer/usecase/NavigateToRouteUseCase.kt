package com.vkondrav.ram.app.screen.drawer.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.app.common.state.DrawerStateWrapper


class NavigateToRouteUseCase(
    private val navController: NavController,
    private val drawerState: DrawerStateWrapper,
) {
    operator fun invoke(route: String) {
        navController.navigate(route)
        drawerState.close()
    }
}
