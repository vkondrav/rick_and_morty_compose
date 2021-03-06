package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.generated.fragment.CharacterFragment
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

    object Factory {

        @Throws(IllegalArgumentException::class)
        operator fun invoke(fragment: CharacterFragment, favorites: Flow<Set<String>>) =
            with(fragment) {
                val id = requireNotNull(id) { "Missing id" }
                RamCharacter(
                    id = id,
                    name = requireNotNull(name) { "Missing name" },
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

    object Adapter {
        fun favorite(ramCharacter: RamCharacter) = with(ramCharacter) {
            FavoriteCharacter(
                id,
                name,
                status,
                species,
                image,
            )
        }
    }
}
