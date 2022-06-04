package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetailsTransformer
import com.vkondrav.playground.room.ram.FavoriteLocationsDao

class FetchCharacterDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val ramCharacterDetailsTransformer: RamCharacterDetailsTransformer,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamCharacterDetails> = runCatching {
        val favoriteLocations = favoriteLocationsDao.getIds().toSet()

        ramCharacterDetailsTransformer(
            ramRepository.fetchCharacterDetails(id),
            favoriteLocations,
        )
    }
}
