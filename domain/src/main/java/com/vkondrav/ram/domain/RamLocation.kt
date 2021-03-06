package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.generated.fragment.LocationFragment
import com.vkondrav.ram.room.FavoriteLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

data class RamLocation(
    val id: String,
    val name: String,
    val dimension: String?,
    val isFavorite: Flow<Boolean>,
) {

    object Factory {

        @Throws(IllegalArgumentException::class)
        operator fun invoke(fragment: LocationFragment, favorites: Flow<Set<String>>) =
            RamLocation(
                id = requireNotNull(fragment.id) { "Missing Id" },
                name = requireNotNull(fragment.name) { "Missing Name" },
                dimension = fragment.dimension,
                isFavorite = favorites.map { it.contains(fragment.id) }.distinctUntilChanged(),
            )

        operator fun invoke(favoriteLocation: FavoriteLocation, favorites: Flow<Set<String>>) =
            with(favoriteLocation) {
                RamLocation(
                    id = id,
                    name = name,
                    dimension = dimension,
                    isFavorite = favorites.map { it.contains(id) }.distinctUntilChanged(),
                )
            }
    }

    object Adapter {

        fun favorite(ramLocation: RamLocation) = with(ramLocation) {
            FavoriteLocation(
                id = id,
                name = name,
                dimension = dimension,
            )
        }
    }
}
