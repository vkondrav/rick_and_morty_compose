package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import com.vkondrav.playground.room.ram.FavoritesDao
import timber.log.Timber

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
    private val favoritesDao: FavoritesDao,
) {

    suspend operator fun invoke(
        page: Int,
    ): Result<List<RamCharacter>> = runCatching {
        val favorites = favoritesDao.getCharacterIds().toSet()

        ramRepository.fetchCharacters(page)
            .mapNotNull { result ->
                runCatching {
                    RamCharacter(
                        result.characterFragment,
                        favorites = favorites,
                    )
                }.onFailure {
                    Timber.e(it.message)
                }.getOrNull()
            }
    }
}
