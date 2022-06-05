package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

class TransformCharactersUseCase(
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
