package com.vkondrav.ram.theme.controller.usecase

import com.vkondrav.ram.theme.controller.core.ThemeController

class ToggleThemeUseCase(
    private val themeController: ThemeController,
) {
    operator fun invoke() {
        themeController.toggleTheme()
    }
}
