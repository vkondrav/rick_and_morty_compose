package com.vkondrav.ram.app.theme.controller.usecase

import com.vkondrav.ram.app.theme.controller.core.ThemeController

class FetchThemeStateUseCase(
    private val themeController: ThemeController,
) {
    operator fun invoke(initialSystemSetting: Boolean) =
        themeController.isThemeDark(initialSystemSetting)
}
