package com.vkondrav.playground.app.screen.episode_details.usecase

import com.vkondrav.playground.domain.RamEpisodeDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import com.vkondrav.playground.room.ram.mapToSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchEpisodeDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamEpisodeDetails.SourceConstructor,
) {

    private val favoriteEpisodesFlow by lazy {
        favoriteEpisodesDao.getIds().mapToSet()
    }
    private val favoriteCharactersFlow by lazy {
        favoriteCharactersDao.getIds().mapToSet()
    }

    operator fun invoke(
        id: String,
    ): Result<Flow<RamEpisodeDetails>> = runCatching {
        ramRepository.fetchEpisodeDetails(id).map {
            sourceConstructor(
                it,
                favoriteEpisodesFlow,
                favoriteCharactersFlow,
            )
        }
    }

}
