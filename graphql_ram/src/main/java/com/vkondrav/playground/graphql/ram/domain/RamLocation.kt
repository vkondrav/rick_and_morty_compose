package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamLocation(
    val id: String,
    val name: String,
    val dimension: String?,
) {

    @Throws(InvalidDataException::class)
    internal constructor(fragment: LocationFragment) : this(
        id = fragment.id ?: throw InvalidDataException("Missing Id"),
        name = fragment.name ?: throw InvalidDataException("Missing Name"),
        dimension = fragment.dimension,
    )

}
