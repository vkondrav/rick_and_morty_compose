package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import kotlinx.coroutines.flow.Flow
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
                isFavorite = favorites.map { it.contains(fragment.id) },
            )
    }
}
