package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.screen.location_details.nav.toLocationDetailsScreen

class NavigateToLocationDetailsUseCase(
    private val navigationStateHolder: Navigator,
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
