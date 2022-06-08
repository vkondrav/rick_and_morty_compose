package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.domain.RamEpisode
import com.vkondrav.playground.domain.RamPage
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import kotlinx.coroutines.flow.map

class FetchEpisodesUseCase(
    private val ramRepository: RamRepository,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamPage.SourceConstructor,
) {

    private val favorites by lazy { favoriteEpisodesDao.getIds().map { it.toSet() } }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamEpisode>> = runCatching {
        sourceConstructor.episodes(
            ramRepository.fetchEpisodes(page),
            favorites,
        )
    }
}
