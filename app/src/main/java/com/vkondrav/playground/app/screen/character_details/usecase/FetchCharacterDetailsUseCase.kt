package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetails

class FetchCharacterDetailsUseCase(
    private val ramRepository: RamRepository,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamCharacterDetails> = runCatching {
        RamCharacterDetails(
            ramRepository.fetchCharacterDetails(id),
            favorite = false,
        )
    }
}
