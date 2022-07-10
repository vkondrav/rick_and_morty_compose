package com.vkondrav.ram.app.theme.controller.usecase

import com.vkondrav.ram.app.theme.controller.core.ThemeController

class ToggleThemeUseCase(
    private val themeController: ThemeController,
) {
    operator fun invoke() {
        themeController.toggleTheme()
    }
}
