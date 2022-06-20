package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.common.state.DrawerStateHolder
import com.vkondrav.ram.app.common.navigation.Navigator


class NavigateToRouteUseCase(
    private val navigationStateHolder: Navigator,
    private val drawerState: DrawerStateHolder,
) {
    operator fun invoke(route: String) {
        navigationStateHolder.navigate(route)
        drawerState.close()
    }
}
