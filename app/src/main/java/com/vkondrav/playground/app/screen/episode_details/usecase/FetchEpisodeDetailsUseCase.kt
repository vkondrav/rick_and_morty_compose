package com.vkondrav.playground.app.screen.episode_details.usecase

import com.vkondrav.playground.domain.RamEpisodeDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao

class FetchEpisodeDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamEpisodeDetails.SourceConstructor,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamEpisodeDetails> = runCatching {
        val favoriteCharacters = favoriteCharactersDao.getIds().toSet()
        val favoriteEpisodes = favoriteEpisodesDao.getIds().toSet()

        sourceConstructor(
            ramRepository.fetchEpisodeDetails(id),
            favoriteCharacters,
            favoriteEpisodes,
        )
    }
}
