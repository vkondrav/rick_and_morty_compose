package com.vkondrav.ram.episode.all.usecase

import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.FavoriteEpisodesDao
import com.vkondrav.ram.common.util.mapToSet

class FetchEpisodesUseCase(
    private val ramRepository: RamRepository,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val factory: RamPage.Factory,
) {

    private val favorites by lazy { favoriteEpisodesDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamEpisode>> = runCatching {
        factory.episodes(
            ramRepository.fetchEpisodes(page),
            favorites,
        )
    }
}
