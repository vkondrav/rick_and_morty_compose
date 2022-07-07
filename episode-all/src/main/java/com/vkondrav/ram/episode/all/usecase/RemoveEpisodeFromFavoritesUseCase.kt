package com.vkondrav.ram.episode.all.usecase

import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.room.FavoriteEpisodesDao
import com.vkondrav.ram.snackbar.usecase.ShowSnackbarMessageUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RemoveEpisodeFromFavoritesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val showSnackbarMessageUseCase: ShowSnackbarMessageUseCase,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(episode: RamEpisode) {
        launch {
            favoriteEpisodesDao.delete(episode.id)
            showSnackbarMessageUseCase("${episode.title} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
