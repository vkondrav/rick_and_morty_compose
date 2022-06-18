package com.vkondrav.ram.app.screen.episode_details.usecase

import com.vkondrav.ram.domain.RamEpisodeDetails
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.ram.FavoriteCharactersDao
import com.vkondrav.ram.room.ram.FavoriteEpisodesDao
import com.vkondrav.ram.room.ram.mapToSet
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
