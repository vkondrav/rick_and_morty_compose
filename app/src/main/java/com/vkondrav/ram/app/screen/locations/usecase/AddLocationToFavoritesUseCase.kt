package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.room.FavoriteLocationsDao
import com.vkondrav.ram.snackbar.usecase.ShowSnackbarMessageUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddLocationToFavoritesUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val showSnackbarMessageUseCase: ShowSnackbarMessageUseCase,
    private val adapter: RamLocation.Adapter,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        location: RamLocation,
    ) {
        launch {
            favoriteLocationsDao.insert(adapter.favorite(location))
            showSnackbarMessageUseCase("${location.name} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
