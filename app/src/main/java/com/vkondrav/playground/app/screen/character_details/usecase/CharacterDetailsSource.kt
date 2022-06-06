package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.CollapsableViewItem
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.playground.app.screen.episodes.usecase.EpisodeViewItemsConstructor
import com.vkondrav.playground.app.screen.locations.usecase.LocationViewItemsConstructor
import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.domain.RamLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterDetailsSource(
    private val fetchCharacterDetailsUseCase: FetchCharacterDetailsUseCase,
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
            locationItem(TextResource.Resource(R.string.current_location))
        }

    private val RamCharacterDetails.originLocation
        get() = origin?.run {
            locationItem(TextResource.Resource(R.string.origin))
        }

    private val RamCharacterDetails.episodesList
        get() = CollapsableViewItem(
            title = TextResource.Resource(R.string.episodes),
            items = episodeViewItemsConstructor(episodes.takeLast(MAX_EPISODES).reversed()),
            open = false,
        )

    private fun RamLocation.locationItem(title: TextResource) = CollapsableViewItem(
        title = title,
        items = listOf(locationViewItemsConstructor(this)),
        open = false,
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}
