package com.vkondrav.ram.app.screen.drawer.usecase

import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.screen.drawer.composable.DrawerMenuViewItem
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.navigation.Routes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrawerMenuSource(
    private val navigateToRouteFromDrawerUseCase: NavigateToRouteFromDrawerUseCase,
) {

    operator fun invoke(): Flow<List<ComposableItem>> = flow {
        emit(
            listOf(
                TextResource.Resource(R.string.characters) to Routes.Character.All(),
                TextResource.Resource(R.string.episodes) to Routes.Character.All(),
                TextResource.Resource(R.string.locations) to Routes.Locations.All(),
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
