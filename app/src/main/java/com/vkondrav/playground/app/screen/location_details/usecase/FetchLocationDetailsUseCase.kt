package com.vkondrav.playground.app.screen.location_details.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamLocationDetails
import com.vkondrav.playground.room.ram.FavoriteLocationsDao

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<RamLocationDetails> = runCatching {
        val favorites = favoriteLocationsDao.getIds().toSet()
        RamLocationDetails(
            ramRepository.fetchLocationDetails(id),
            favorites,
        )
    }
}
