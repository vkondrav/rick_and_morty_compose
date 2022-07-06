package com.vkondrav.ram.app.screen.episodes.usecase

import com.vkondrav.ram.navigation.Navigator
import com.vkondrav.ram.app.screen.episode_details.nav.toEpisodeDetailsScreen

class NavigateToEpisodeDetailsUseCase(
    private val navigator: Navigator,
) {
    operator fun invoke(id: String, title: String) {
        navigator.navigate(
            toEpisodeDetailsScreen(
                id,
                title,
            ),
        )
    }
}
