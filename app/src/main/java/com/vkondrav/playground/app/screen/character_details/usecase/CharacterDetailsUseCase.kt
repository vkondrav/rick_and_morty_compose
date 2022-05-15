package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.graphql.ram.RamRepository

class CharacterDetailsUseCase(
    private val ramRepository: RamRepository,
) {
    suspend operator fun invoke(
        id: String,
    ): Result<List<ComposableItem>> = runCatching {
        ramRepository.fetchCharacterDetails(id)
        emptyList()
    }
}