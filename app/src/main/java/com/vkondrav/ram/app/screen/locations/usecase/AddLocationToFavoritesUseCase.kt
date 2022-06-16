package com.vkondrav.ram.app.screen.locations.usecase

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.room.ram.FavoriteLocation
import com.vkondrav.ram.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class AddLocationToFavoritesUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val snackbarHostState: SnackbarHostState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(
        location: RamLocation,
    ) {
        launch {
            favoriteLocationsDao.insert(location.favoriteLocation)
            snackbarHostState.showSnackbar("${location.name} added to favorites")
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
