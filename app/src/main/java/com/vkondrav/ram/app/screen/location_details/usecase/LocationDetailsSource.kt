package com.vkondrav.ram.app.screen.location_details.usecase

import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.common.collapsable_drawer.composable.CollapsableViewItem
import com.vkondrav.ram.app.common.utils.TextResource
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.app.screen.characters.usecase.CharactersViewItemConstructor
import com.vkondrav.ram.domain.RamLocationDetails
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
