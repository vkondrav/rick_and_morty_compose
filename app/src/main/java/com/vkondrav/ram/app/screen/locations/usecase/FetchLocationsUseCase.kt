package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.room.FavoriteLocationsDao
import com.vkondrav.ram.util.mapToSet

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val adapter: RamPage.Adapter,
) {

    private val favorites by lazy { favoriteLocationsDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamLocation>> = runCatching {
        adapter.locations(
            ramRepository.fetchLocations(page),
            favorites,
        )
    }
}
