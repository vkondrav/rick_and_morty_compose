package com.vkondrav.ram.app.screen.main.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.app.common.navigation.Navigator

class HandleNavigationCommandsUseCase(
    private val navigator: Navigator,
) {
    suspend operator fun invoke(navController: NavController) =
        navigator.handleNavigationCommands(navController)
}
