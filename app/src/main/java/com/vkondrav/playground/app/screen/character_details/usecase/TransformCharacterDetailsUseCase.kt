package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.common.composable.CollapsableViewItem
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.playground.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.playground.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.playground.app.screen.locations.composable.LocationViewItem
import com.vkondrav.playground.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.domain.RamLocation

class TransformCharacterDetailsUseCase(
    private val navigateToLocationDetailsUseCase: NavigateToLocationDetailsUseCase,
    private val navigateToEpisodeDetailsUseCase: NavigateToEpisodeDetailsUseCase,
) {

    operator fun invoke(
        details: RamCharacterDetails,
    ): ComposableItem = ContentViewItem(
        items = with(details) {
            listOfNotNull(
                header,
                currentLocation,
                originLocation,
                episodesList,
            )
        },
    )

    private val RamCharacterDetails.header
        get() = CharacterDetailsViewItem(
            character = character,
        )

    private val RamCharacterDetails.currentLocation
        get() = location?.run {
            locationItem("Current Location")
        }

    private val RamCharacterDetails.originLocation
        get() = origin?.run {
            locationItem("Origin")
        }

    private val RamCharacterDetails.episodesList
        get() = CollapsableViewItem(
            title = "Episodes",
            items = episodes.takeLast(MAX_EPISODES).reversed().map {
                EpisodeViewItem(episode = it,
                    onClickAction = {
                        navigateToEpisodeDetailsUseCase(
                            it.id,
                            it.title,
                        )
                    },
                )
            },
            open = false,
        )

    private fun RamLocation.locationItem(title: String) = CollapsableViewItem(
        title = title,
        items = listOf(
            LocationViewItem(
                location = this,
                onClickAction = {
                    navigateToLocationDetailsUseCase(
                        id = id,
                        title = name,
                    )
                },
            ),
        ),
        open = false,
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}