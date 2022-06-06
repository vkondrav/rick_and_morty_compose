package com.vkondrav.playground.app.screen.favorite_characters.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.playground.app.screen.characters.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.playground.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.playground.domain.RamCharacter
import com.vkondrav.playground.room.ram.FavoriteCharacter
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchFavoriteCharactersUseCase(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val sourceConstructor: RamCharacter.SourceConstructor,
    private val navigateToCharacterDetailsUseCase: NavigateToCharacterDetailsUseCase,
    private val handleCharacterFavoritesUseCase: HandleCharacterFavoritesUseCase,
) {

    operator fun invoke(): Result<Flow<List<ComposableItem>>> = runCatching {
        favoriteCharactersDao.getAll().map { favoriteCharacters ->
            favoriteCharacters.map { it.viewItem }
        }
    }

    private val FavoriteCharacter.viewItem
        get() = with(sourceConstructor(this)) {
            CharacterViewItem(
                character = this,
                onClickAction = {
                    navigateToCharacterDetailsUseCase(
                        id,
                        name,
                    )
                },
                onFavoriteAction = { isFavorite ->
                    handleCharacterFavoritesUseCase(this, isFavorite)
                },
            )
        }
}
