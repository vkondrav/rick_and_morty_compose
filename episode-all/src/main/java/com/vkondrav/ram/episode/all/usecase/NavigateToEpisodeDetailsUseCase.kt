package com.vkondrav.ram.episode.all.usecase

import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToEpisodeDetailsUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {
    operator fun invoke(id: String, title: String) {
        navigateToRouteUseCase(Routes.Episodes.Details(id, title))
    }
}
