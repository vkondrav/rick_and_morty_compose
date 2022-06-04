package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamLocation(
    val id: String,
    val name: String,
    val dimension: String?,
    var favorite: Boolean,
) {

    @Throws(InvalidDataException::class)
    constructor(fragment: LocationFragment, favorites: Set<String>) : this(
        id = fragment.id ?: throw InvalidDataException("Missing Id"),
        name = fragment.name ?: throw InvalidDataException("Missing Name"),
        dimension = fragment.dimension,
        favorite = favorites.contains(fragment.id),
    )

}

object RamLocationTransformer {
    operator fun invoke(fragment: LocationFragment, favorites: Set<String>) =
        RamLocation(fragment, favorites)
}
