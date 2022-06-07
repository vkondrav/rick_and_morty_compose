package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import com.vkondrav.playground.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import timber.log.Timber

class FetchCharacterDetailsUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamCharacterDetails.SourceConstructor,
) {

    operator fun invoke(id: String): Result<Flow<RamCharacterDetails>> = runCatching {
        val favoriteCharactersFlow = favoriteCharactersDao.getIdsAsFlow().map { it.toSet() }
        val favoriteEpisodesFlow = favoriteEpisodesDao.getIdsAsFlow().map { it.toSet() }
        val favoriteLocationsFlow = favoriteLocationsDao.getIdsAsFlow().map { it.toSet() }
        val characterDetailsFlow = ramRepository.fetchCharacterDetails(id)

        combine(
            characterDetailsFlow,
            favoriteCharactersFlow,
            favoriteEpisodesFlow,
            favoriteLocationsFlow,
        ) { details, favoriteCharacters, favoriteEpisodes, favoriteLocations ->

            Timber.d("$favoriteCharacters|$favoriteEpisodes|$favoriteLocations")

            sourceConstructor(
                details,
                favoriteCharacters,
                favoriteEpisodes,
                favoriteLocations,
            )
        }
    }
}
