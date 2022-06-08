package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.domain.RamLocation
import com.vkondrav.playground.domain.RamPage
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.flow.map

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val sourceConstructor: RamPage.SourceConstructor,
) {

    private val favorites by lazy { favoriteLocationsDao.getIds().map { it.toSet() } }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamLocation>> = runCatching {
        sourceConstructor.locations(
            ramRepository.fetchLocations(page),
            favorites,
        )
    }
}
