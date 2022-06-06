package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.domain.RamLocation
import com.vkondrav.playground.domain.RamPage
import com.vkondrav.playground.room.ram.FavoriteLocationsDao

class FetchLocationsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val sourceConstructor: RamPage.SourceConstructor,
) {

    private var favorites: Set<String>? = null

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamLocation>> = runCatching {
        val favorites = favorites ?: favoriteLocationsDao.getIds().toSet().also {
            favorites = it
        }

        sourceConstructor.locations(
            ramRepository.fetchLocations(page),
            favorites,
        )
    }
}
