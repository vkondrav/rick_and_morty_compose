package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.navigation.Navigator
import com.vkondrav.ram.app.screen.location_details.nav.toLocationDetailsScreen

class NavigateToLocationDetailsUseCase(
    private val navigator: Navigator,
) {
    operator fun invoke(id: String, title: String) {
        navigator.navigate(
            toLocationDetailsScreen(
                id,
                title,
            ),
        )
    }
}
