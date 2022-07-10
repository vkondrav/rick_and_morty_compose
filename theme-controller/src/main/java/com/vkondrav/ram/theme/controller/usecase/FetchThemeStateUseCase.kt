package com.vkondrav.ram.theme.controller.usecase

import com.vkondrav.ram.theme.controller.core.ThemeController

class FetchThemeStateUseCase(
    private val themeController: ThemeController,
) {
    operator fun invoke(initialSystemSetting: Boolean) =
        themeController.isThemeDark(initialSystemSetting)
}
