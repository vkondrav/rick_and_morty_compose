package com.vkondrav.ram.episode.details.usecase

import com.vkondrav.ram.common.util.mapToSet
import com.vkondrav.ram.domain.RamEpisodeDetails
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.room.FavoriteEpisodesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchEpisodeDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val factory: RamEpisodeDetails.Factory,
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
            factory(
                it,
                favoriteEpisodesFlow,
                favoriteCharactersFlow,
            )
        }
    }
}
