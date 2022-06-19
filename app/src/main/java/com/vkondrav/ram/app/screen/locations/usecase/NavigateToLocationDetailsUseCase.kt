package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.app.common.state.NavigationStateHolder
import com.vkondrav.ram.app.screen.location_details.nav.toLocationDetailsScreen

class NavigateToLocationDetailsUseCase(
    private val navigationStateHolder: NavigationStateHolder,
) {
    operator fun invoke(id: String, title: String) {
        navigationStateHolder.navigate(
            toLocationDetailsScreen(
                id,
                title,
            ),
        )
    }
}
