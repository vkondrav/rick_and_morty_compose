package com.vkondrav.ram.domain

import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.ram.graphql.error.InvalidDataException
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

    object SourceConstructor {

        @Throws(InvalidDataException::class)
        operator fun invoke(fragment: LocationFragment, favorites: Flow<Set<String>>) =
            RamLocation(
                id = fragment.id ?: throw InvalidDataException("Missing Id"),
                name = fragment.name ?: throw InvalidDataException("Missing Name"),
                dimension = fragment.dimension,
                isFavorite = favorites.map { it.contains(fragment.id) }.distinctUntilChanged(),
            )

        @Throws(InvalidDataException::class)
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
}
