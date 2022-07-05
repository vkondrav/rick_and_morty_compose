package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.common.util.mapToSet

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val factory: RamPage.Factory,
) {

    private val favorites by lazy { favoriteCharactersDao.getIds().mapToSet() }

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamCharacter>> = runCatching {
        factory.characters(
            ramRepository.fetchCharacters(page),
            favorites,
        )
    }
}
