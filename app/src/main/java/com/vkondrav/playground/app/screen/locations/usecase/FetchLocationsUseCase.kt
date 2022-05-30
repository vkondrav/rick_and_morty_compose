package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamLocation

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
) {

    suspend operator fun invoke(page: Int): Result<List<RamLocation>> = runCatching {
        ramRepository.fetchLocations(page)
    }
}
