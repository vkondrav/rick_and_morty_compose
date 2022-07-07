package com.vkondrav.ram.location.all.factory

import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.location.all.composable.LocationViewItem
import com.vkondrav.ram.location.all.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.ram.location.all.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.ram.domain.RamLocation

class LocationViewItemFactory(
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
