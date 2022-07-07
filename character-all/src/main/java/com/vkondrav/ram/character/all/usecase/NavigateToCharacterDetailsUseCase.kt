package com.vkondrav.ram.character.all.usecase

import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase

class NavigateToCharacterDetailsUseCase(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {
    operator fun invoke(id: String, title: String) {
        navigateToRouteUseCase(
            Routes.Character.Details(
                id,
                title,
            ),
        )
    }
}
