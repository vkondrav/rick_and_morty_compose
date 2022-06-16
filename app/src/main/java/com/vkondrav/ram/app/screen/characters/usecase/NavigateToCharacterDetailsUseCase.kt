package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.common.state.AppState
import com.vkondrav.ram.app.screen.character_details.nav.toCharacterDetailsScreen

class NavigateToCharacterDetailsUseCase(
    private val appState: AppState,
) {
    operator fun invoke(id: String, title: String) {
        appState.navigate(
            toCharacterDetailsScreen(
                id,
                title,
            ),
        )
    }
}
