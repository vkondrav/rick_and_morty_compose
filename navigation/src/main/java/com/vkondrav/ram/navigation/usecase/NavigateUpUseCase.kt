package com.vkondrav.ram.navigation.usecase

import com.vkondrav.ram.navigation.Navigator

interface NavigateUpUseCase {
    operator fun invoke()
}

internal fun navigateUpUseCase(
    navigator: Navigator,
) = object : NavigateUpUseCase {
    override operator fun invoke() = navigator.navigateUp()
}
