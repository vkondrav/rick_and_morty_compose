package com.vkondrav.ram.app.screen.favorite_locations.usecase

import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.screen.locations.composable.LocationViewItem
import com.vkondrav.ram.app.screen.locations.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.ram.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.room.ram.FavoriteLocation
import com.vkondrav.ram.room.ram.FavoriteLocationsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavoriteLocationsUseCase(
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val sourceConstructor: RamLocation.SourceConstructor,
    private val navigateToLocationDetailsUseCase: NavigateToLocationDetailsUseCase,
    private val handleLocationFavoriteUseCase: HandleLocationFavoriteUseCase,
) {

    operator fun invoke(): Result<Flow<List<ComposableItem>>> = runCatching {
        favoriteLocationsDao.getAll().map { favoriteLocations ->
            favoriteLocations.map { it.viewItem }
        }
    }

    private val FavoriteLocation.viewItem
        get() = with(sourceConstructor(this, flowOf(setOf(id)))) {
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