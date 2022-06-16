package com.vkondrav.ram.app.screen.episodes.usecase

import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.ram.RamRepository
import com.vkondrav.ram.room.ram.FavoriteEpisodesDao
import com.vkondrav.ram.room.ram.mapToSet

class FetchEpisodesUseCase(
    private val ramRepository: RamRepository,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamPage.SourceConstructor,
) {

    private val favorites by lazy { favoriteEpisodesDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamEpisode>> = runCatching {
        sourceConstructor.episodes(
            ramRepository.fetchEpisodes(page),
            favorites,
        )
    }
}
