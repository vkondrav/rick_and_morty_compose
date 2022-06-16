package com.vkondrav.ram.app.screen.episodes.usecase

import com.vkondrav.ram.app.common.state.AppState
import com.vkondrav.ram.app.screen.episode_details.nav.toEpisodeDetailsScreen

class NavigateToEpisodeDetailsUseCase(
    private val appState: AppState,
) {
    operator fun invoke(id: String, title: String) {
        appState.navigate(
            toEpisodeDetailsScreen(
                id,
                title,
            ),
        )
    }
}
