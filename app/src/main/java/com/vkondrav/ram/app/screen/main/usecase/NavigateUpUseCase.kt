package com.vkondrav.ram.app.screen.main.usecase

import com.vkondrav.ram.app.common.navigation.Navigator

class NavigateUpUseCase(
    private val navigator: Navigator,
) {
    operator fun invoke() = navigator.navigateUp()
}
