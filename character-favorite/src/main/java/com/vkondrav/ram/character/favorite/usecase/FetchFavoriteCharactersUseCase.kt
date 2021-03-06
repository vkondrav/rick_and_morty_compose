package com.vkondrav.ram.character.favorite.usecase

import com.vkondrav.ram.character.common.composable.CharacterViewItem
import com.vkondrav.ram.character.common.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.character.common.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.FavoriteCharacter
import com.vkondrav.ram.room.FavoriteCharactersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavoriteCharactersUseCase(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val factory: RamCharacter.Factory,
    private val navigateToCharacterDetailsUseCase: NavigateToCharacterDetailsUseCase,
    private val handleCharacterFavoritesUseCase: HandleCharacterFavoritesUseCase,
) {

    operator fun invoke(): Result<Flow<List<ComposableItem>>> = runCatching {
        favoriteCharactersDao.getAll().map { favoriteCharacters ->
            favoriteCharacters.map { it.viewItem }
        }
    }

    private val FavoriteCharacter.viewItem
        get() = with(factory(this, flowOf(setOf(id)))) {
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
