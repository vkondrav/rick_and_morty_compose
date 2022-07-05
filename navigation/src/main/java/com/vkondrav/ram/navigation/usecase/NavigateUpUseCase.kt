package com.vkondrav.ram.navigation.usecase

import com.vkondrav.ram.navigation.Navigator

class NavigateUpUseCase(
    private val navigator: Navigator,
) {
    operator fun invoke() = navigator.navigateUp()
}
