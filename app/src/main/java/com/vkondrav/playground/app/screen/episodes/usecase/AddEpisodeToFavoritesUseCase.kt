package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.domain.RamEpisode
import com.vkondrav.playground.room.ram.FavoriteEpisode
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddEpisodeToFavoritesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(episode: RamEpisode) {
        launch {
            favoriteEpisodesDao.insert(episode.favoriteEpisode)
            appState.showSnackbar("${episode.title} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        appState.showSnackbar(throwable.message ?: "unknown error has occurred")
    }

    private val RamEpisode.favoriteEpisode
        get() = FavoriteEpisode(
            id = id,
            title = title,
            airDate = airDate,
        )
}
