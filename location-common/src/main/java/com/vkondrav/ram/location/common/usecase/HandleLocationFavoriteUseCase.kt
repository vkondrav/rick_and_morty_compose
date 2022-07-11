package com.vkondrav.ram.location.common.usecase

import com.vkondrav.ram.domain.RamLocation

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
