package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.snackbar.SnackbarController
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.room.FavoriteLocationsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddLocationToFavoritesUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val snackbarController: com.vkondrav.ram.snackbar.SnackbarController,
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
            snackbarController.showMessage("${location.name} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
