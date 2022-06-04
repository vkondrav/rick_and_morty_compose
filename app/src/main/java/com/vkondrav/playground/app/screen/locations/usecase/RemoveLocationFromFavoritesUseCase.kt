package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.graphql.ram.domain.RamLocation
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RemoveLocationFromFavoritesUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        location: RamLocation,
    ) {
        launch {
            favoriteLocationsDao.delete(location.id)
            appState.showSnackbar("${location.name} removed from favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
