package com.vkondrav.playground.app.screen.episode_details.usecase

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.CollapsableViewItem
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.characters.usecase.CharactersViewItemConstructor
import com.vkondrav.playground.domain.RamEpisodeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

class EpisodeDetailsSource(
    private val fetchEpisodeDetailsUseCase: FetchEpisodeDetailsUseCase,
    private val charactersViewItemConstructor: CharactersViewItemConstructor,
) {

    operator fun invoke(id: String): Result<Flow<List<ComposableItem>>> = runCatching {
        fetchEpisodeDetailsUseCase(id).getOrThrow().map { details ->
            with(details) {
                listOf(
                    charactersList,
                )
            }
        }
    }

    private val RamEpisodeDetails.charactersList
        get() = CollapsableViewItem(
            title = TextResource.Resource(R.string.characters),
            items = charactersViewItemConstructor(characters.take(MAX_CHARACTERS_COUNT)),
            open = true,
        )

    companion object {
        private const val MAX_CHARACTERS_COUNT = 5
    }
}
