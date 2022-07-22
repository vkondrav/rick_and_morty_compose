package com.vkondrav.ram.location.all.usecase

import com.vkondrav.ram.common.util.mapToSet
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.FavoriteLocationsDao

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val factory: RamPage.Factory,
) {

    private val favorites by lazy { favoriteLocationsDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamLocation>> = runCatching {
        factory.locations(
            ramRepository.fetchLocations(page),
            favorites,
        )
    }
}
