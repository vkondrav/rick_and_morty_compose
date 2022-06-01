package com.vkondrav.playground.app.screen.location_details.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamLocationDetails

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamLocationDetails> = runCatching {
        RamLocationDetails(ramRepository.fetchLocationDetails(id))
    }
}
