package com.vkondrav.ram.app.screen.episode_details.usecase

import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.common.collapsable_drawer.composable.CollapsableViewItem
import com.vkondrav.ram.app.common.utils.TextResource
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.app.screen.characters.usecase.CharactersViewItemConstructor
import com.vkondrav.ram.domain.RamEpisodeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpisodeDetailsSource(
    private val fetchEpisodeDetailsUseCase: FetchEpisodeDetailsUseCase,
    private val fetchCollapsableDrawerState: FetchCollapsableDrawerStateUseCase,
    private val handleCollapsableDrawerUseCase: HandleCollapsableDrawerUseCase,
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

    private val RamEpisodeDetails.charactersList: ComposableItem
        get() {
            val id = "episode_${episode.id}_characters"

            return CollapsableViewItem(
                id = id,
                title = TextResource.Resource(R.string.characters),
                items = charactersViewItemConstructor(characters.take(MAX_CHARACTERS_COUNT)),
                open = fetchCollapsableDrawerState(id),
                onClickAction = { isOpen ->
                    handleCollapsableDrawerUseCase(id, isOpen)
                },
            )
        }

    companion object {
        private const val MAX_CHARACTERS_COUNT = 5
    }
}