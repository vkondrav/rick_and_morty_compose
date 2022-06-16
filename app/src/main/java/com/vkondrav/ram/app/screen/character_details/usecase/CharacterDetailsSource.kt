package com.vkondrav.ram.app.screen.character_details.usecase

import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.common.collapsable_drawer.composable.CollapsableViewItem
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.app.common.utils.TextResource
import com.vkondrav.ram.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.ram.app.screen.episodes.usecase.EpisodeViewItemsConstructor
import com.vkondrav.ram.app.screen.locations.usecase.LocationViewItemsConstructor
import com.vkondrav.ram.domain.RamCharacterDetails
import com.vkondrav.ram.domain.RamLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterDetailsSource(
    private val fetchCharacterDetailsUseCase: FetchCharacterDetailsUseCase,
    private val fetchCollapsableDrawerState: FetchCollapsableDrawerStateUseCase,
    private val handleCollapsableDrawerUseCase: HandleCollapsableDrawerUseCase,
    private val locationViewItemsConstructor: LocationViewItemsConstructor,
    private val episodeViewItemsConstructor: EpisodeViewItemsConstructor,
) {

    operator fun invoke(id: String): Result<Flow<List<ComposableItem>>> = runCatching {
        fetchCharacterDetailsUseCase(id).getOrThrow().map { details ->
            with(details) {
                listOfNotNull(
                    header,
                    currentLocation,
                    originLocation,
                    episodesList,
                )
            }
        }
    }

    private val RamCharacterDetails.header
        get() = CharacterDetailsViewItem(
            character = character,
        )

    private val RamCharacterDetails.currentLocation
        get() = location?.run {
            locationItem(
                "character_${character.id}_location",
                TextResource.Resource(R.string.current_location),
            )
        }

    private val RamCharacterDetails.originLocation
        get() = origin?.run {
            locationItem("character_${character.id}_origin", TextResource.Resource(R.string.origin))
        }

    private val RamCharacterDetails.episodesList: ComposableItem
        get() {
            val id = "character_${character.id}_episodes"
            return CollapsableViewItem(
                id = id,
                title = TextResource.Resource(R.string.episodes),
                items = episodeViewItemsConstructor(episodes.takeLast(MAX_EPISODES).reversed()),
                open = fetchCollapsableDrawerState(id),
                onClickAction = { isOpen ->
                    handleCollapsableDrawerUseCase(id, isOpen)
                },
            )
        }

    private fun RamLocation.locationItem(id: String, title: TextResource) = CollapsableViewItem(
        id = id,
        title = title,
        items = listOf(locationViewItemsConstructor(this)),
        open = fetchCollapsableDrawerState(id),
        onClickAction = { isOpen ->
            handleCollapsableDrawerUseCase(id, isOpen)
        },
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}
