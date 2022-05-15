package com.vkondrav.playground.app.screen.characters.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.screen.character_details.nav.routeToCharacterDetailsScreen
import com.vkondrav.playground.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.playground.graphql.ram.RamRepository
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

class CharactersUseCase(
    private val ramRepository: RamRepository,
    private val appState: AppState,
) {

    suspend operator fun invoke(
        page: Int,
    ): Result<List<ComposableItem>> = runCatching {
        ramRepository.fetchCharacters(page).viewItems
    }

    private val List<RamCharacter>.viewItems
        get() = map { character ->
            CharacterViewItem(
                character = character,
                onClickAction = {
                    appState.navigate(routeToCharacterDetailsScreen(character.id))
                },
            )
        }
}