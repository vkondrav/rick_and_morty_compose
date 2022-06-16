package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.ram.RamRepository
import com.vkondrav.ram.room.ram.FavoriteCharactersDao
import com.vkondrav.ram.room.ram.mapToSet

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val transformer: RamPage.SourceConstructor,
) {

    private val favorites by lazy { favoriteCharactersDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamCharacter>> = runCatching {
        transformer.characters(
            ramRepository.fetchCharacters(page),
            favorites,
        )
    }
}
