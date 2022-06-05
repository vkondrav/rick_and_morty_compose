package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.playground.graphql.ram.CharactersResponse
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamCharacter(
    val id: String,
    val name: String,
    val status: String?,
    val species: String?,
    val image: String?,
    var favorite: Boolean,
) {

    @Throws(InvalidDataException::class)
    constructor(fragment: CharacterFragment, favorites: Set<String>) : this(
        id = fragment.id ?: throw InvalidDataException("missing id"),
        name = fragment.name ?: throw InvalidDataException("missing name"),
        status = fragment.status,
        species = fragment.species,
        image = fragment.image,
        favorite = favorites.contains(fragment.id),
    )

}

object RamCharacterTransformer {

    @Throws(InvalidDataException::class)
    operator fun invoke(fragment: CharacterFragment, favorites: Set<String>) =
        RamCharacter(fragment, favorites)

    operator fun invoke(response: CharactersResponse, favorites: Set<String>) =
        with(response) {
            RamPage(
                info.prev,
                info.next,
                characters.mapNotNull { fragment ->
                    runCatching {
                        invoke(fragment, favorites)
                    }.onFailure {
                        Timber.e(it)
                    }.getOrNull()
                },
            )
        }
}
