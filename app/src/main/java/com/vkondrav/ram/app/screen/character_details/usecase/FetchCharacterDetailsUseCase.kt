package com.vkondrav.ram.app.screen.character_details.usecase

import com.vkondrav.ram.domain.RamCharacterDetails
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.ram.FavoriteCharactersDao
import com.vkondrav.ram.room.ram.FavoriteEpisodesDao
import com.vkondrav.ram.room.ram.FavoriteLocationsDao
import com.vkondrav.ram.room.ram.mapToSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchCharacterDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamCharacterDetails.SourceConstructor,
) {

    private val favoriteCharactersFlow by lazy {
        favoriteCharactersDao.getIds().mapToSet()
    }
    private val favoriteEpisodesFlow by lazy {
        favoriteEpisodesDao.getIds().mapToSet()
    }
    private val favoriteLocationsFlow by lazy {
        favoriteLocationsDao.getIds().mapToSet()
    }

    operator fun invoke(id: String): Result<Flow<RamCharacterDetails>> = runCatching {
        ramRepository.fetchCharacterDetails(id).map {
            sourceConstructor(
                it,
                favoriteCharactersFlow,
                favoriteEpisodesFlow,
                favoriteLocationsFlow,
            )
        }
    }
}
