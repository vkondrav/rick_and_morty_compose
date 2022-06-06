package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import com.vkondrav.playground.room.ram.FavoriteLocationsDao

class FetchCharacterDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val transformer: RamCharacterDetails.SourceConstructor,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamCharacterDetails> = runCatching {
        val favoriteCharacters = favoriteCharactersDao.getIds().toSet()
        val favoriteLocations = favoriteLocationsDao.getIds().toSet()
        val favoriteEpisodes = favoriteEpisodesDao.getIds().toSet()

        transformer(
            ramRepository.fetchCharacterDetails(id),
            favoriteCharacters,
            favoriteLocations,
            favoriteEpisodes,
        )
    }
}
