package com.vkondrav.ram.app.screen.location_details.usecase

import com.vkondrav.ram.domain.RamLocationDetails
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.room.FavoriteLocationsDao
import com.vkondrav.ram.room.mapToSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val sourceConstructor: RamLocationDetails.SourceConstructor,
) {

    private val favoriteLocationsFlow by lazy {
        favoriteLocationsDao.getIds().mapToSet()
    }
    private val favoriteCharactersFlow by lazy {
        favoriteCharactersDao.getIds().mapToSet()
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
