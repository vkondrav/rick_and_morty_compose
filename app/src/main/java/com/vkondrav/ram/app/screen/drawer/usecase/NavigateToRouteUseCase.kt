package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.common.state.DrawerStateHolder
import com.vkondrav.ram.app.common.state.NavigationStateHolder


class NavigateToRouteUseCase(
    private val navigationStateHolder: NavigationStateHolder,
    private val drawerState: DrawerStateHolder,
) {
    operator fun invoke(route: String) {
        navigationStateHolder.navigate(route)
        drawerState.close()
    }
}
