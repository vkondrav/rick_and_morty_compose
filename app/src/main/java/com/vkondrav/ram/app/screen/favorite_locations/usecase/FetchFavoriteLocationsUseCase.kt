package com.vkondrav.ram.app.screen.favorite_locations.usecase

import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.app.screen.locations.composable.LocationViewItem
import com.vkondrav.ram.app.screen.locations.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.ram.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.room.FavoriteLocation
import com.vkondrav.ram.room.FavoriteLocationsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavoriteLocationsUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val factory: RamLocation.Factory,
    private val navigateToLocationDetailsUseCase: NavigateToLocationDetailsUseCase,
    private val handleLocationFavoriteUseCase: HandleLocationFavoriteUseCase,
) {

    operator fun invoke(): Result<Flow<List<ComposableItem>>> = runCatching {
        favoriteLocationsDao.getAll().map { favoriteLocations ->
            favoriteLocations.map { it.viewItem }
        }
    }

    private val FavoriteLocation.viewItem
        get() = with(factory(this, flowOf(setOf(id)))) {
            LocationViewItem(
                location = this,
                onClickAction = {
                    navigateToLocationDetailsUseCase(
                        id,
                        name,
                    )
                },
                onFavoriteAction = { isFavorite ->
                    handleLocationFavoriteUseCase(this, isFavorite)
                },
            )
        }
}
