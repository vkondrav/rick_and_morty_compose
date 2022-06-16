package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.common.state.AppState

class NavigateToRouteUseCase(
    private val appState: AppState,
) {
    operator fun invoke(route: String) {
        appState.navigate(route)
        appState.closeDrawer()
    }
}
