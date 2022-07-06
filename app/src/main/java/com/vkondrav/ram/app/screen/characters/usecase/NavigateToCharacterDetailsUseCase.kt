package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.screen.character_details.nav.toCharacterDetailsScreen
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToCharacterDetailsUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {
    operator fun invoke(id: String, title: String) {
        navigateToRouteUseCase(
            toCharacterDetailsScreen(
                id,
                title,
            ),
        )
    }
}
