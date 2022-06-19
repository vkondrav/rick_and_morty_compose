package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.FavoriteCharacter
import com.vkondrav.ram.room.FavoriteCharactersDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddCharacterToFavoritesUseCase(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val snackbarMessageStateHolder: SnackbarMessageStateHolder,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        character: RamCharacter,
    ) {
        launch {
            favoriteCharactersDao.insert(character.favoriteCharacter)
            snackbarMessageStateHolder.showSnackbar("${character.name} added to favorites")
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
