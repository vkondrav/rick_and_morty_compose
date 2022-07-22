package com.vkondrav.ram.character.common.usecase

import com.vkondrav.ram.domain.RamCharacter

class HandleCharacterFavoritesUseCase(
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase,
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase,
) {

    operator fun invoke(character: RamCharacter, isFavorite: Boolean) {
        when (isFavorite) {
            true -> addCharacterToFavoritesUseCase(character)
            false -> removeCharacterFromFavoritesUseCase(character)
        }
    }
}
