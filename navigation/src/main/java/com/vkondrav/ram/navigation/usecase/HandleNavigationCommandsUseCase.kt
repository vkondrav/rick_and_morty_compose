package com.vkondrav.ram.navigation.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.navigation.Navigator

class HandleNavigationCommandsUseCase(
    private val navigator: Navigator,
) {
    suspend operator fun invoke(navController: NavController) =
        navigator.handleNavigationCommands(navController)
}
