package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import com.vkondrav.playground.graphql.ram.domain.RamCharacterTransformer
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import timber.log.Timber

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val transformer: RamCharacterTransformer,
) {

    suspend operator fun invoke(
        page: Int,
    ): Result<List<RamCharacter>> = runCatching {
        val favorites = favoriteCharactersDao.getIds().toSet()

        ramRepository.fetchCharacters(page)
            .mapNotNull { result ->
                runCatching {
                    transformer(result.characterFragment, favorites)
                }.onFailure {
                    Timber.e(it)
                }.getOrNull()
            }
    }
}
