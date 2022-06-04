package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamEpisode
import timber.log.Timber

class FetchEpisodesUseCase(
    private val ramRepository: RamRepository,
) {

    suspend operator fun invoke(page: Int): Result<List<RamEpisode>> = runCatching {
        ramRepository.fetchEpisodes(page).mapNotNull {
            runCatching {
                RamEpisode(it.episodeFragment, emptySet())
            }.onFailure {
                Timber.e(it)
            }.getOrNull()
        }
    }
}
