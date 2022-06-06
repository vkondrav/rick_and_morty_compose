package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.domain.RamEpisode
import com.vkondrav.playground.domain.RamPage
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao

class FetchEpisodesUseCase(
    private val ramRepository: RamRepository,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamPage.SourceConstructor,
) {

    private var favorites: Set<String>? = null

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamEpisode>> = runCatching {
        val favorites = favorites ?: favoriteEpisodesDao.getIds().toSet().also {
            favorites = it
        }

        sourceConstructor.episodes(
            ramRepository.fetchEpisodes(page),
            favorites,
        )
    }
}
