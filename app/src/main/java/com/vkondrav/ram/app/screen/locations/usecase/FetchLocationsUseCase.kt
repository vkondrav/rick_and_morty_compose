package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.room.ram.FavoriteLocationsDao
import com.vkondrav.ram.room.ram.mapToSet

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val sourceConstructor: RamPage.SourceConstructor,
) {

    private val favorites by lazy { favoriteLocationsDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamLocation>> = runCatching {
        sourceConstructor.locations(
            ramRepository.fetchLocations(page),
            favorites,
        )
    }
}
