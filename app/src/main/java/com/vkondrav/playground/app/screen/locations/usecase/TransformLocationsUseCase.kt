package com.vkondrav.playground.app.screen.locations.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.screen.locations.composable.LocationViewItem
import com.vkondrav.playground.graphql.ram.domain.RamLocation

class TransformLocationsUseCase(
    private val navigateToLocationDetailsUseCase: NavigateToLocationDetailsUseCase,
    private val handleLocationFavoriteUseCase: HandleLocationFavoriteUseCase,
) {
    operator fun invoke(
        locations: List<RamLocation>,
    ): ComposableItem = ContentViewItem(
        items = locations.viewItems,
    )

    private val List<RamLocation>.viewItems
        get() = map { location ->
            LocationViewItem(
                location = location,
                onClickAction = {
                    navigateToLocationDetailsUseCase(
                        id = location.id,
                        title = location.name,
                    )
                },
                onFavoriteAction = { isFavorite ->
                    handleLocationFavoriteUseCase(isFavorite, location)
                },
            )
        }

}
