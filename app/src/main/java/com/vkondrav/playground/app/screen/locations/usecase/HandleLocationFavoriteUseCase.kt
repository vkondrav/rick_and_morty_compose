package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.domain.RamLocation

class HandleLocationFavoriteUseCase(
    private val addLocationToFavoritesUseCase: AddLocationToFavoritesUseCase,
    private val removeLocationToFavoritesUseCase: RemoveLocationFromFavoritesUseCase,
) {
    operator fun invoke(isFavorite: Boolean, location: RamLocation) {
        when (isFavorite) {
            true -> addLocationToFavoritesUseCase(location)
            false -> removeLocationToFavoritesUseCase(location)
        }
    }
}
