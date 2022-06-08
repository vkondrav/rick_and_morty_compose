package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.domain.RamCharacter
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.domain.RamPage
import com.vkondrav.playground.room.ram.FavoriteCharactersDao
import kotlinx.coroutines.flow.map

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val transformer: RamPage.SourceConstructor,
) {

    private val favorites by lazy { favoriteCharactersDao.getIds().map { it.toSet() } }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamCharacter>> = runCatching {
        transformer.characters(
            ramRepository.fetchCharacters(page),
            favorites,
        )
    }
}
