package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import com.vkondrav.playground.room.ram.FavoriteCharacter

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
            with(fragment) {
                RamCharacter(
                    id = id ?: throw InvalidDataException("missing id"),
                    name = name ?: throw InvalidDataException("missing name"),
                    status = status,
                    species = species,
                    image = image,
                    favorite = favorites.contains(id),
                )
            }

        operator fun invoke(favoriteCharacter: FavoriteCharacter) =
            with(favoriteCharacter) {
                RamCharacter(
                    id = id,
                    name = name,
                    status = status,
                    species = species,
                    image = image,
                    favorite = true,
                )
            }
    }
}
