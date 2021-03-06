package com.vkondrav.ram.location.details.usecase

import com.vkondrav.ram.common.util.mapToSet
import com.vkondrav.ram.domain.RamLocationDetails
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.room.FavoriteLocationsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchLocationDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val factory: RamLocationDetails.Factory,
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
            factory(
                it,
                favoriteLocationsFlow,
                favoriteCharactersFlow,
            )
        }
    }
}
