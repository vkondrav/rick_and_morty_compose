package com.vkondrav.ram.app.screen.episodes.usecase

import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.room.FavoriteEpisodesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RemoveEpisodeFromFavoritesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val snackbarMessageStateHolder: SnackbarMessageStateHolder,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(episode: RamEpisode) {
        launch {
            favoriteEpisodesDao.delete(episode.id)
            snackbarMessageStateHolder.showSnackbar("${episode.title} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
