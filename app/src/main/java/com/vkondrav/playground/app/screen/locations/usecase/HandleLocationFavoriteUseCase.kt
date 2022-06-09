package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.domain.RamLocation

class HandleLocationFavoriteUseCase(
    private val addLocationToFavoritesUseCase: AddLocationToFavoritesUseCase,
    private val removeLocationToFavoritesUseCase: RemoveLocationFromFavoritesUseCase,
) {
    operator fun invoke(location: RamLocation, isFavorite: Boolean) {
        when (isFavorite) {
            true -> addLocationToFavoritesUseCase(location)
            false -> removeLocationToFavoritesUseCase(location)
        }
    }
}
