package com.vkondrav.playground.app.screen.location_details.usecase

import com.vkondrav.playground.domain.RamLocationDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val sourceConstructor: RamLocationDetails.SourceConstructor,
) {

    private val favoriteLocationsFlow by lazy {
        favoriteLocationsDao.getIds().map { it.toSet() }
    }
    private val favoriteCharactersFlow by lazy {
        favoriteCharactersDao.getIds().map { it.toSet() }
    }

    operator fun invoke(
        id: String,
    ): Result<Flow<RamLocationDetails>> = runCatching {
        ramRepository.fetchLocationDetails(id).map {
            sourceConstructor(
                it,
                favoriteLocationsFlow,
                favoriteCharactersFlow,
            )
        }
    }
}
