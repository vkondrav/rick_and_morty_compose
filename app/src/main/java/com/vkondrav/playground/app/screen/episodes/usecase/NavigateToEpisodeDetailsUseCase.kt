package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.screen.episode_details.nav.toEpisodeDetailsScreen

class NavigateToEpisodeDetailsUseCase(
    private val appState: AppState
) {
    operator fun invoke(id: String, title: String) {
        appState.navigate(
            toEpisodeDetailsScreen(
                id,
                title,
            )
        )
    }
}