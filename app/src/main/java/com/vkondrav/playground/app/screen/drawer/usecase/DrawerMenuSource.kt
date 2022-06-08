package com.vkondrav.playground.app.screen.drawer.usecase

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.characters.nav.charactersScreen
import com.vkondrav.playground.app.screen.drawer.composable.DrawerMenuViewItem
import com.vkondrav.playground.app.screen.episodes.nav.episodesScreen
import com.vkondrav.playground.app.screen.locations.nav.locationsScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrawerMenuSource(
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
) {

    operator fun invoke(): Flow<List<ComposableItem>> = flow {
        emit(
            listOf(
                TextResource.Resource(R.string.characters) to charactersScreen.route,
                TextResource.Resource(R.string.episodes) to episodesScreen.route,
                TextResource.Resource(R.string.locations) to locationsScreen.route,
            ).map { (title, route) ->
                DrawerMenuViewItem(
                    title = title,
                    onClickAction = {
                        navigateToRouteUseCase(route)
                    },
                )
            },
        )
    }

}
