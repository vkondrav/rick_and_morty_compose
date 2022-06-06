package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.domain.RamCharacter
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.domain.RamPage
import com.vkondrav.playground.room.ram.FavoriteCharactersDao

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val transformer: RamCharacter.SourceTransformer,
) {

    private var favorites: Set<String>? = null

    suspend operator fun invoke(
        page: Int,
    ): Result<RamPage<RamCharacter>> = runCatching {
        val favorites = favorites ?: favoriteCharactersDao.getIds().toSet().also {
            favorites = it
        }

        transformer(
            ramRepository.fetchCharacters(page),
            favorites,
        )
    }
}
