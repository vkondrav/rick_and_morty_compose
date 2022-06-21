package com.vkondrav.ram.app.screen.main.usecase

import com.vkondrav.ram.app.design.ThemeController

class ToggleThemeUseCase(
    private val themeController: ThemeController,
) {
    operator fun invoke() {
        themeController.toggleTheme()
    }
}
