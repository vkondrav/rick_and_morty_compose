package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToRouteFromDrawerUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
    private val drawerState: DrawerController,
) {
    operator fun invoke(route: String) {
        navigateToRouteUseCase(route)
        drawerState.close()
    }
}
