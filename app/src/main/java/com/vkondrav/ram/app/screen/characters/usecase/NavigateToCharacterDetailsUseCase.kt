package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.common.state.NavigationStateHolder
import com.vkondrav.ram.app.screen.character_details.nav.toCharacterDetailsScreen

class NavigateToCharacterDetailsUseCase(
    private val navigationStateHolder: NavigationStateHolder,
) {
    operator fun invoke(id: String, title: String) {
        navigationStateHolder.navigate(
            toCharacterDetailsScreen(
                id,
                title,
            ),
        )
    }
}
