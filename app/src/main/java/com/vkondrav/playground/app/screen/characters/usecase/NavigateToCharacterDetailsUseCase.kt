package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.screen.character_details.nav.toCharacterDetailsScreen

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
