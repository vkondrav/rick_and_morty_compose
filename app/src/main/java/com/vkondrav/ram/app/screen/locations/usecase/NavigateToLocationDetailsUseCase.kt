package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.app.screen.location_details.nav.toLocationDetailsScreen
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToLocationDetailsUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {
    operator fun invoke(id: String, title: String) {
        navigateToRouteUseCase(
            toLocationDetailsScreen(
                id,
                title,
            ),
        )
    }
}
