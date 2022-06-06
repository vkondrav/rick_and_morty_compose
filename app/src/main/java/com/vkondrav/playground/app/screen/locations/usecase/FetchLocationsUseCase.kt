package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.domain.RamLocation
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import timber.log.Timber

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val sourceTransformer: RamLocation.SourceTransformer,
) {

    suspend operator fun invoke(page: Int): Result<List<RamLocation>> = runCatching {
        val favoriteLocations = favoriteLocationsDao.getIds().toSet()

        ramRepository.fetchLocations(page).mapNotNull { result ->
            runCatching {
                sourceTransformer(result.locationFragment, favoriteLocations)
            }.onFailure {
                Timber.e(it)
            }.getOrNull()

        }
    }
}
