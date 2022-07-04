package com.vkondrav.ram.app.screen.characters.adapter

import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.ram.app.screen.characters.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.domain.RamCharacter

class CharactersViewItemAdapter(
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
