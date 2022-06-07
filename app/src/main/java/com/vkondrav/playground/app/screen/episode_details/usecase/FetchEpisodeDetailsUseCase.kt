package com.vkondrav.playground.app.screen.episode_details.usecase

import com.vkondrav.playground.domain.RamEpisodeDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class FetchEpisodeDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamEpisodeDetails.SourceConstructor,
) {

    operator fun invoke(
        id: String,
    ): Result<Flow<RamEpisodeDetails>> = runCatching {
        val episodeDetailsFlow = ramRepository.fetchEpisodeDetails(id)
        val favoriteEpisodesFlow = favoriteEpisodesDao.getIdsAsFlow().map { it.toSet() }
        val favoriteCharactersFlow = favoriteCharactersDao.getIdsAsFlow().map { it.toSet() }

        combine(
            episodeDetailsFlow,
            favoriteEpisodesFlow,
            favoriteCharactersFlow,
        ) { episodeDetails, favoriteEpisodes, favoriteCharacters ->

            sourceConstructor(
                episodeDetails,
                favoriteEpisodes,
                favoriteCharacters,
            )

        }
    }

}
