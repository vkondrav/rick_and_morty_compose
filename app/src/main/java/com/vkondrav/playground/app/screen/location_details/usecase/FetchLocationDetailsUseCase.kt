package com.vkondrav.playground.app.screen.location_details.usecase

import com.vkondrav.playground.domain.RamLocationDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val sourceConstructor: RamLocationDetails.SourceConstructor,
) {
    operator fun invoke(
        id: String,
    ): Result<Flow<RamLocationDetails>> = runCatching {
        val locationDetailsFlow = ramRepository.fetchLocationDetails(id)
        val favoriteLocationsFlow = favoriteLocationsDao.getIdsAsFlow().map { it.toSet() }
        val favoriteCharactersFlow = favoriteCharactersDao.getIdsAsFlow().map { it.toSet() }

        combine(
            locationDetailsFlow,
            favoriteLocationsFlow,
            favoriteCharactersFlow,
        ) { locationDetails, favoriteLocations, favoriteCharacters ->
            sourceConstructor(
                locationDetails,
                favoriteLocations,
                favoriteCharacters,
            )
        }
    }
}
