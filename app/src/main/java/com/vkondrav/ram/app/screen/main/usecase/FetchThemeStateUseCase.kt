package com.vkondrav.ram.app.screen.main.usecase

import com.vkondrav.ram.app.design.ThemeController

class FetchThemeStateUseCase(
    private val themeController: ThemeController,
) {
    operator fun invoke(initialSystemSetting: Boolean) =
        themeController.isThemeDark(initialSystemSetting)
}
