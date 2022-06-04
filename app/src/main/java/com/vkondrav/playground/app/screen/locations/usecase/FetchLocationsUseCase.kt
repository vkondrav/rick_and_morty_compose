package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamLocation
import com.vkondrav.playground.graphql.ram.domain.RamLocationTransformer
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import timber.log.Timber

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val ramLocationTransformer: RamLocationTransformer,
    private val favoriteLocationsDao: FavoriteLocationsDao,
) {

    suspend operator fun invoke(page: Int): Result<List<RamLocation>> = runCatching {
        val favorites = favoriteLocationsDao.getIds().toSet()

        ramRepository.fetchLocations(page).mapNotNull { result ->
            runCatching {
                ramLocationTransformer(result.locationFragment, favorites)
            }.onFailure {
                Timber.e(it)
            }.getOrNull()

        }
    }
}
