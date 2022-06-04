package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.graphql.ram.domain.RamCharacter

class HandleCharacterFavoritesUseCase(
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase,
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase,
) {

    operator fun invoke(character: RamCharacter, isFavorite: Boolean) {
        when(isFavorite) {
            true -> addCharacterToFavoritesUseCase(character)
            false -> removeCharacterFromFavoritesUseCase(character)
        }
    }
}
