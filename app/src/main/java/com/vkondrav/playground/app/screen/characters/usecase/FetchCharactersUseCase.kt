package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
) {

    suspend operator fun invoke(
        page: Int,
    ): Result<List<RamCharacter>> = runCatching {
        ramRepository.fetchCharacters(page)
    }
}