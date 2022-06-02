package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import com.vkondrav.playground.room.ram.FavoriteCharacter
import com.vkondrav.playground.room.ram.FavoritesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddCharacterToFavoritesUseCase(
    private val favoritesDao: FavoritesDao,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        character: RamCharacter,
    ) {
        launch {
            favoritesDao.insertCharacter(character.favoriteCharacter)
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    private val RamCharacter.favoriteCharacter
        get() = FavoriteCharacter(
            id = id,
            name = name,
            status = status,
            species = species,
            image = image,
        )
}
