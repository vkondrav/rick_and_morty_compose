package com.vkondrav.ram.app.screen.episodes.usecase

import com.vkondrav.ram.app.common.state.NavigationStateHolder
import com.vkondrav.ram.app.screen.episode_details.nav.toEpisodeDetailsScreen

class NavigateToEpisodeDetailsUseCase(
    private val navigationStateHolder: NavigationStateHolder,
) {
    operator fun invoke(id: String, title: String) {
        navigationStateHolder.navigate(
            toEpisodeDetailsScreen(
                id,
                title,
            ),
        )
    }
}
