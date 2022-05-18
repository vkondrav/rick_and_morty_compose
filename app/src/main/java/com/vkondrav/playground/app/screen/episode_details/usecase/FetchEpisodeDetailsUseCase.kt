package com.vkondrav.playground.app.screen.episode_details.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamEpisodeDetails

class FetchEpisodeDetailsUseCase(
    private val ramRepository: RamRepository,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamEpisodeDetails> = runCatching {
        ramRepository.fetchEpisodeDetails(id)
    }
}