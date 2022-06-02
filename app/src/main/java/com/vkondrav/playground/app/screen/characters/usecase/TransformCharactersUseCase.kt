package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

class TransformCharactersUseCase(
    private val navigateToCharacterDetailsUseCase: NavigateToCharacterDetailsUseCase,
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase,
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase,
) {

    operator fun invoke(
        characters: List<RamCharacter>,
    ): ComposableItem = ContentViewItem(
        items = characters.viewItems,
    )

    private val List<RamCharacter>.viewItems
        get() = map { character ->
            CharacterViewItem(
                character = character,
                onClickAction = {
                    navigateToCharacterDetailsUseCase(
                        id = character.id,
                        title = character.name,
                    )
                },
                onFavoriteAction = { favorite ->
                    when (favorite) {
                        true -> addCharacterToFavoritesUseCase(character)
                        false -> removeCharacterFromFavoritesUseCase(character)
                    }
                },
            )
        }
}
