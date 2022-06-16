package com.vkondrav.ram.app.screen.episodes.usecase

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.room.ram.FavoriteEpisode
import com.vkondrav.ram.room.ram.FavoriteEpisodesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddEpisodeToFavoritesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val snackbarHostState: SnackbarHostState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(episode: RamEpisode) {
        launch {
            favoriteEpisodesDao.insert(episode.favoriteEpisode)
            snackbarHostState.showSnackbar("${episode.title} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    private val RamEpisode.favoriteEpisode
        get() = FavoriteEpisode(
            id = id,
            title = title,
            airDate = airDate,
        )
}
