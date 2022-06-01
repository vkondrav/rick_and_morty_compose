package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import timber.log.Timber

class FetchCharactersUseCase(
    private val ramRepository: RamRepository,
) {

    suspend operator fun invoke(
        page: Int,
    ): Result<List<RamCharacter>> = runCatching {
        ramRepository.fetchCharacters(page)
            .mapNotNull { result ->
                runCatching {
                    RamCharacter(
                        result.characterFragment,
                        favorite = false,
                    )
                }.onFailure {
                    Timber.e(it.message)
                }.getOrNull()
            }
    }
}
