package com.vkondrav.ram.location.details.usecase

import com.vkondrav.ram.character.common.factory.CharacterViewItemFactory
import com.vkondrav.ram.collapsable.drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.collapsable.drawer.view.CollapsableViewItem
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.domain.RamLocationDetails
import com.vkondrav.ram.location.details.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationDetailsSource(
    private val fetchLocationDetailsUseCase: FetchLocationDetailsUseCase,
    private val fetchCollapsableDrawerState: FetchCollapsableDrawerStateUseCase,
    private val handleCollapsableDrawerUseCase: HandleCollapsableDrawerUseCase,
    private val characterViewItemFactory: CharacterViewItemFactory,
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
                items = characterViewItemFactory(characters.take(MAX_CHARACTERS_COUNT)),
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
