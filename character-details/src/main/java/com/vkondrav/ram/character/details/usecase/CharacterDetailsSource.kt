package com.vkondrav.ram.character.details.usecase

import com.vkondrav.ram.character.details.R
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.collapsable.drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.character.details.composable.CharacterDetailsViewItem
import com.vkondrav.ram.episode.all.factory.EpisodeViewItemFactory
import com.vkondrav.ram.location.all.factory.LocationViewItemFactory
import com.vkondrav.ram.collapsable.drawer.view.CollapsableViewItem
import com.vkondrav.ram.domain.RamCharacterDetails
import com.vkondrav.ram.domain.RamLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterDetailsSource(
    private val fetchCharacterDetailsUseCase: FetchCharacterDetailsUseCase,
    private val fetchCollapsableDrawerState: FetchCollapsableDrawerStateUseCase,
    private val handleCollapsableDrawerUseCase: HandleCollapsableDrawerUseCase,
    private val locationViewItemFactory: LocationViewItemFactory,
    private val episodeViewItemFactory: EpisodeViewItemFactory,
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
                items = episodeViewItemFactory(episodes.takeLast(MAX_EPISODES).reversed()),
                open = fetchCollapsableDrawerState(id),
                onClickAction = { isOpen ->
                    handleCollapsableDrawerUseCase(id, isOpen)
                },
            )
        }

    private fun RamLocation.locationItem(id: String, title: TextResource) = CollapsableViewItem(
        id = id,
        title = title,
        items = listOf(locationViewItemFactory(this)),
        open = fetchCollapsableDrawerState(id),
        onClickAction = { isOpen ->
            handleCollapsableDrawerUseCase(id, isOpen)
        },
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}
