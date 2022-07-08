package com.vkondrav.ram.drawer.source

import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.drawer.R
import com.vkondrav.ram.drawer.view.DrawerMenuViewItem
import com.vkondrav.ram.drawer.usecase.NavigateToRouteFromDrawerUseCase
import com.vkondrav.ram.navigation.Routes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrawerMenuSource(
    private val navigateToRouteFromDrawerUseCase: NavigateToRouteFromDrawerUseCase,
) {

    operator fun invoke(): Flow<List<ComposableItem>> = flow {
        emit(
            listOf(
                TextResource.Resource(R.string.menu_item_characters) to Routes.Character.All(),
                TextResource.Resource(R.string.menu_item_episodes) to Routes.Character.All(),
                TextResource.Resource(R.string.menu_item_locations) to Routes.Locations.All(),
            ).map { (title, route) ->
                DrawerMenuViewItem(
                    title = title,
                    onClickAction = {
                        navigateToRouteFromDrawerUseCase(route)
                    },
                )
            },
        )
    }

}
