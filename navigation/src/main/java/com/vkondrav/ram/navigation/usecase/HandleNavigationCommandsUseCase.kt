package com.vkondrav.ram.navigation.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.navigation.Navigator

interface HandleNavigationCommandsUseCase {
    suspend operator fun invoke(navController: NavController)
}

internal fun handleNavigationCommandsUseCase(
    navigator: Navigator,
) = object : HandleNavigationCommandsUseCase {
    override suspend fun invoke(navController: NavController) =
        navigator.handleNavigationCommands(navController)
}
