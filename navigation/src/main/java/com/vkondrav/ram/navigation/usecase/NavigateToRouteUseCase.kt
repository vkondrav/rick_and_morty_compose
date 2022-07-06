package com.vkondrav.ram.navigation.usecase

import com.vkondrav.ram.navigation.Navigator

interface NavigateToRouteUseCase {
    operator fun invoke(route: String)
}

internal fun navigateToRouteUseCase(navigator: Navigator) = object : NavigateToRouteUseCase {
    override fun invoke(route: String) {
        navigator.navigate(route)
    }
}
