package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamLocation(
    val id: String,
    val name: String,
    val dimension: String?,
    var favorite: Boolean,
) {

    object SourceConstructor {

        @Throws(InvalidDataException::class)
        operator fun invoke(fragment: LocationFragment, favorites: Set<String>) =
            RamLocation(
                id = fragment.id ?: throw InvalidDataException("Missing Id"),
                name = fragment.name ?: throw InvalidDataException("Missing Name"),
                dimension = fragment.dimension,
                favorite = favorites.contains(fragment.id),
            )
    }
}
