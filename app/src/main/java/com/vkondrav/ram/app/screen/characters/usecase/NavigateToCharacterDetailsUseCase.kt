package com.vkondrav.ram.app.screen.characters.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.app.screen.character_details.nav.toCharacterDetailsScreen

class NavigateToCharacterDetailsUseCase(
    private val navController: NavController,
) {
    operator fun invoke(id: String, title: String) {
        navController.navigate(
            toCharacterDetailsScreen(
                id,
                title,
            ),
        )
    }
}
