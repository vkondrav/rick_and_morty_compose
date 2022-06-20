package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.app.common.navigation.Navigator


class NavigateToRouteUseCase(
    private val navigationStateHolder: Navigator,
    private val drawerState: DrawerController,
) {
    operator fun invoke(route: String) {
        navigationStateHolder.navigate(route)
        drawerState.close()
    }
}
