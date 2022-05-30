package com.vkondrav.playground.app.screen.drawer.usecase

import com.vkondrav.playground.app.common.state.AppState

class NavigateToRouteUseCase(
    private val appState: AppState,
) {
    operator fun invoke(route: String) {
        appState.navigate(route)
        appState.closeDrawer()
    }
}
