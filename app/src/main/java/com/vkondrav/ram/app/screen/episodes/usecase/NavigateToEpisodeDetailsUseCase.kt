package com.vkondrav.ram.app.screen.episodes.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.app.screen.episode_details.nav.toEpisodeDetailsScreen

class NavigateToEpisodeDetailsUseCase(
    private val navController: NavController,
) {
    operator fun invoke(id: String, title: String) {
        navController.navigate(
            toEpisodeDetailsScreen(
                id,
                title,
            ),
        )
    }
}
