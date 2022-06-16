package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.app.common.state.AppState
import com.vkondrav.ram.app.screen.location_details.nav.toLocationDetailsScreen

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
