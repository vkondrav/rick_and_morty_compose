package com.vkondrav.ram.character.all.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.snackbar.usecase.ShowSnackbarMessageUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddCharacterToFavoritesUseCase(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val showSnackbarMessageUseCase: ShowSnackbarMessageUseCase,
    private val adapter: RamCharacter.Adapter,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        character: RamCharacter,
    ) {
        launch {
            favoriteCharactersDao.insert(adapter.favorite(character))
            showSnackbarMessageUseCase("${character.name} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}