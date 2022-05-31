package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.screen.location_details.nav.toLocationDetailsScreen

class NavigateToLocationDetailsUseCase(
    private val appState: AppState,
) {
    operator fun invoke(id: String, title: String) {
        appState.navigate(
            toLocationDetailsScreen(
                id,
                title,
            ),
        )
    }
}
