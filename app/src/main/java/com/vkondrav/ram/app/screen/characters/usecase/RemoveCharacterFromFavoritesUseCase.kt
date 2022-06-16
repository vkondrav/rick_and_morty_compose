package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.common.state.AppState
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.ram.FavoriteCharactersDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RemoveCharacterFromFavoritesUseCase(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        character: RamCharacter,
    ) {
        launch {
            favoriteCharactersDao.delete(character.id)
            appState.showSnackbar("${character.name} removed from favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
