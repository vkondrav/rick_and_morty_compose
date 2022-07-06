package com.vkondrav.ram.app.screen.episodes.usecase

import com.vkondrav.ram.app.screen.episode_details.nav.toEpisodeDetailsScreen
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToEpisodeDetailsUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {
    operator fun invoke(id: String, title: String) {
        navigateToRouteUseCase(
            toEpisodeDetailsScreen(
                id,
                title,
            ),
        )
    }
}
