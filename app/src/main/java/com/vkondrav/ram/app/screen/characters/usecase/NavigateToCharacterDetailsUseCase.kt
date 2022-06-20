package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.screen.character_details.nav.toCharacterDetailsScreen

class NavigateToCharacterDetailsUseCase(
    private val navigationStateHolder: Navigator,
) {
    operator fun invoke(id: String, title: String) {
        navigationStateHolder.navigate(
            toCharacterDetailsScreen(
                id,
                title,
            ),
        )
    }
}
