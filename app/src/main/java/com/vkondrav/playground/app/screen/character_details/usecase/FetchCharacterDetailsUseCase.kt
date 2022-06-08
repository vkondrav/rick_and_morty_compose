package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
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
        favoriteCharactersDao.getIds().map { it.toSet() }
    }
    private val favoriteEpisodesFlow by lazy {
        favoriteEpisodesDao.getIds().map { it.toSet() }
    }
    private val favoriteLocationsFlow by lazy {
        favoriteLocationsDao.getIds().map { it.toSet() }
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
