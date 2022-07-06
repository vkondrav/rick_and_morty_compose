package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.R
import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.common.ui.TextResource
import com.vkondrav.ram.app.screen.characters.nav.charactersScreen
import com.vkondrav.ram.app.screen.drawer.composable.DrawerMenuViewItem
import com.vkondrav.ram.app.screen.episodes.nav.episodesScreen
import com.vkondrav.ram.app.screen.locations.nav.locationsScreen
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
