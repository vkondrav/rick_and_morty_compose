package com.vkondrav.ram.location.all.usecase

import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToLocationDetailsUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {
    operator fun invoke(id: String, title: String) {
        navigateToRouteUseCase(Routes.Locations.Details(id, title))
    }
}
