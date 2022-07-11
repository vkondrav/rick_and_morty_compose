package com.vkondrav.ram.character.common.factory

import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.character.common.composable.CharacterViewItem
import com.vkondrav.ram.character.common.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.character.common.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.domain.RamCharacter

class CharacterViewItemFactory(
    private val navigateToCharacterDetailsUseCase: NavigateToCharacterDetailsUseCase,
    private val handleCharacterFavoritesUseCase: HandleCharacterFavoritesUseCase,
) {

    operator fun invoke(
        character: RamCharacter,
    ): ComposableItem
        = CharacterViewItem(
            character = character,
            onClickAction = {
                navigateToCharacterDetailsUseCase(
                    id = character.id,
                    title = character.name,
                )
            },
            onFavoriteAction = { isFavorite ->
                handleCharacterFavoritesUseCase(character, isFavorite)
            },
        )

    operator fun invoke(
        characters: List<RamCharacter>,
    ): List<ComposableItem> = characters.map { invoke(it) }
}
