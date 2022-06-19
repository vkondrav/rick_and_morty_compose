package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.room.FavoriteLocation
import com.vkondrav.ram.room.FavoriteLocationsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddLocationToFavoritesUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val snackbarMessageStateHolder: SnackbarMessageStateHolder,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        location: RamLocation,
    ) {
        launch {
            favoriteLocationsDao.insert(location.favoriteLocation)
            snackbarMessageStateHolder.showSnackbar("${location.name} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    private val RamLocation.favoriteLocation
        get() = FavoriteLocation(
            id = id,
            name = name,
            dimension = dimension,
        )
}
