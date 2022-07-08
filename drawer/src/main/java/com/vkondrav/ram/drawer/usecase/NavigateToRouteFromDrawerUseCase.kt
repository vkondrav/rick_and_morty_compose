package com.vkondrav.ram.drawer.usecase

import com.vkondrav.ram.drawer.core.DrawerController
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

interface NavigateToRouteFromDrawerUseCase {
    operator fun invoke(route: String)
}

internal fun navigateToRouteFromDrawerUseCase(
    navigateToRouteUseCase: NavigateToRouteUseCase,
    drawerState: DrawerController,
) = object : NavigateToRouteFromDrawerUseCase {
    override operator fun invoke(route: String) {
        navigateToRouteUseCase(route)
        drawerState.close()
    }
}
