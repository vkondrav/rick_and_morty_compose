package com.vkondrav.playground.app.screen.location_details.usecase

import com.vkondrav.playground.domain.RamLocationDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteLocationsDao

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val sourceTransformer: RamLocationDetails.SourceTransformer,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamLocationDetails> = runCatching {
        val favoriteLocations = favoriteLocationsDao.getIds().toSet()
        val favoriteCharacters = favoriteCharactersDao.getIds().toSet()

        sourceTransformer(
            ramRepository.fetchLocationDetails(id),
            favoriteLocations,
            favoriteCharacters,
        )
    }
}
