package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamEpisode
import com.vkondrav.playground.graphql.ram.domain.RamEpisodeTransformer
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import timber.log.Timber

class FetchEpisodesUseCase(
    private val ramRepository: RamRepository,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val transformer: RamEpisodeTransformer,
) {

    suspend operator fun invoke(page: Int): Result<List<RamEpisode>> = runCatching {
        ramRepository.fetchEpisodes(page).mapNotNull {
            val favorites = favoriteEpisodesDao.getIds().toSet()
            runCatching {
                transformer(it.episodeFragment, favorites)
            }.onFailure {
                Timber.e(it)
            }.getOrNull()
        }
    }
}
