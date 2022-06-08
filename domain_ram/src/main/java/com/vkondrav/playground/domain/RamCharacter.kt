package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import com.vkondrav.playground.room.ram.FavoriteCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

data class RamCharacter(
    val id: String,
    val name: String,
    val status: String?,
    val species: String?,
    val image: String?,
    val isFavorite: Flow<Boolean>,
) {

    object SourceConstructor {

        @Throws(InvalidDataException::class)
        operator fun invoke(fragment: CharacterFragment, favorites: Flow<Set<String>>) =
            with(fragment) {
                RamCharacter(
                    id = id ?: throw InvalidDataException("missing id"),
                    name = name ?: throw InvalidDataException("missing name"),
                    status = status,
                    species = species,
                    image = image,
                    isFavorite = favorites.map { it.contains(id) }.distinctUntilChanged(),
                )
            }

        operator fun invoke(favoriteCharacter: FavoriteCharacter, favorites: Flow<Set<String>>) =
            with(favoriteCharacter) {
                RamCharacter(
                    id = id,
                    name = name,
                    status = status,
                    species = species,
                    image = image,
                    isFavorite = favorites.map { it.contains(id) }.distinctUntilChanged(),
                )
            }
    }
}
