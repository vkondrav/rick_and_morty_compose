package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamLocation
import timber.log.Timber

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
) {

    suspend operator fun invoke(page: Int): Result<List<RamLocation>> = runCatching {
        ramRepository.fetchLocations(page).mapNotNull { result ->
            runCatching {
                RamLocation(result.locationFragment)
            }.onFailure {
                Timber.e(it)
            }.getOrNull()

        }
    }
}
