package com.vkondrav.playground.app.screen.location_details.usecase

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.collapsable_drawer.composable.CollapsableViewItem
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.common.collapsable_drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.playground.app.common.collapsable_drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.playground.app.screen.characters.usecase.CharactersViewItemConstructor
import com.vkondrav.playground.domain.RamLocationDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationDetailsSource(
    private val fetchLocationDetailsUseCase: FetchLocationDetailsUseCase,
    private val fetchCollapsableDrawerState: FetchCollapsableDrawerStateUseCase,
    private val handleCollapsableDrawerUseCase: HandleCollapsableDrawerUseCase,
    private val charactersViewItemConstructor: CharactersViewItemConstructor,
) {

    operator fun invoke(id: String): Result<Flow<List<ComposableItem>>> = runCatching {
        fetchLocationDetailsUseCase(id).getOrThrow().map { details ->
            with(details) {
                listOf(
                    charactersList,
                )
            }
        }
    }

    private val RamLocationDetails.charactersList: ComposableItem
        get() {
            val id = "location_${location.id}_characters"
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
