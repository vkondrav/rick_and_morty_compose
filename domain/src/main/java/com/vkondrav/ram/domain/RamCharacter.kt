package com.vkondrav.ram.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.ram.graphql.error.InvalidDataException
import com.vkondrav.ram.room.FavoriteCharacter
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
                val id = id ?: throw InvalidDataException("missing id")
                val name = name ?: throw InvalidDataException("missing name")
                RamCharacter(
                    id = id,
                    name = name,
                    status = status,
                    species = species,
                    image = image,
                    isFavorite = favorites.isFavorite(id),
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
                    isFavorite = favorites.isFavorite(id),
                )
            }

        private fun Flow<Set<String>>.isFavorite(id: String) =
            map { it.contains(id) }.distinctUntilChanged()
    }
}
