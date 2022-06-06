package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamCharacter(
    val id: String,
    val name: String,
    val status: String?,
    val species: String?,
    val image: String?,
    var favorite: Boolean,
) {

    object SourceConstructor {

        @Throws(InvalidDataException::class)
        operator fun invoke(fragment: CharacterFragment, favorites: Set<String>) =
            RamCharacter(
                id = fragment.id ?: throw InvalidDataException("missing id"),
                name = fragment.name ?: throw InvalidDataException("missing name"),
                status = fragment.status,
                species = fragment.species,
                image = fragment.image,
                favorite = favorites.contains(fragment.id),
            )
    }
}
