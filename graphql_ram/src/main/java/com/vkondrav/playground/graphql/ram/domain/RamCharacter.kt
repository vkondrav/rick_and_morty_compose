package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamCharacter(
    val id: String,
    val name: String,
    val status: String?,
    val species: String?,
    val image: String?,
) {

    @Throws(InvalidDataException::class)
    internal constructor(fragment: CharacterFragment): this(
        id = fragment.id ?: throw InvalidDataException("missing id"),
        name = fragment.name ?: throw InvalidDataException("missing name"),
        status = fragment.status,
        species = fragment.species,
        image = fragment.image,
    )

}
