package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.CollapsableViewItem
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.playground.app.screen.episodes.usecase.TransformEpisodesUseCase
import com.vkondrav.playground.app.screen.locations.usecase.TransformLocationsUseCase
import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.domain.RamLocation

class TransformCharacterDetailsUseCase(
    private val transformLocationsUseCase: TransformLocationsUseCase,
    private val transformEpisodesUseCase: TransformEpisodesUseCase,
) {

    operator fun invoke(
        details: RamCharacterDetails,
    ): List<ComposableItem> = with(details) {
        listOfNotNull(
            header,
            currentLocation,
            originLocation,
            episodesList,
        )
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
            items = transformEpisodesUseCase(episodes.takeLast(MAX_EPISODES).reversed()),
            open = false,
        )

    private fun RamLocation.locationItem(title: TextResource) = CollapsableViewItem(
        title = title,
        items = listOf(transformLocationsUseCase(this)),
        open = false,
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}
