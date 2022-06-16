package com.vkondrav.ram.app.screen.locations.usecase

import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.screen.locations.composable.LocationViewItem
import com.vkondrav.ram.domain.RamLocation

class LocationViewItemsConstructor(
    private val navigateToLocationDetailsUseCase: NavigateToLocationDetailsUseCase,
    private val handleLocationFavoriteUseCase: HandleLocationFavoriteUseCase,
) {
    operator fun invoke(
        locations: List<RamLocation>,
    ): List<ComposableItem> = locations.viewItems

    operator fun invoke(
        location: RamLocation,
    ): ComposableItem = location.viewItem

    private val RamLocation.viewItem get() = LocationViewItem(
        location = this,
        onClickAction = {
            navigateToLocationDetailsUseCase(
                id = id,
                title = name,
            )
        },
        onFavoriteAction = { isFavorite ->
            handleLocationFavoriteUseCase(this, isFavorite)
        },
    )

    private val List<RamLocation>.viewItems get() = map { it.viewItem }
}