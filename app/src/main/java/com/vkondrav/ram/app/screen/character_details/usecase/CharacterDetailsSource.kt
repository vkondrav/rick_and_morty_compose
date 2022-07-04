package com.vkondrav.ram.app.screen.character_details.usecase

import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.common.collapsable_drawer.composable.CollapsableViewItem
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.util.TextResource
import com.vkondrav.ram.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.ram.app.screen.episodes.adapter.EpisodeViewItemsAdapter
import com.vkondrav.ram.app.screen.locations.adapter.LocationViewItemsAdapter
import com.vkondrav.ram.domain.RamCharacterDetails
import com.vkondrav.ram.domain.RamLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterDetailsSource(
    private val fetchCharacterDetailsUseCase: FetchCharacterDetailsUseCase,
    private val fetchCollapsableDrawerState: FetchCollapsableDrawerStateUseCase,
    private val handleCollapsableDrawerUseCase: HandleCollapsableDrawerUseCase,
    private val locationViewItemsAdapter: LocationViewItemsAdapter,
    private val episodeViewItemsAdapter: EpisodeViewItemsAdapter,
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
                items = episodeViewItemsAdapter(episodes.takeLast(MAX_EPISODES).reversed()),
                open = fetchCollapsableDrawerState(id),
                onClickAction = { isOpen ->
                    handleCollapsableDrawerUseCase(id, isOpen)
                },
            )
        }

    private fun RamLocation.locationItem(id: String, title: TextResource) = CollapsableViewItem(
        id = id,
        title = title,
        items = listOf(locationViewItemsAdapter(this)),
        open = fetchCollapsableDrawerState(id),
        onClickAction = { isOpen ->
            handleCollapsableDrawerUseCase(id, isOpen)
        },
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}
