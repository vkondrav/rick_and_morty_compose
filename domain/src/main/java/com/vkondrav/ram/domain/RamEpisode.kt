package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.error.InvalidDataException
import com.vkondrav.ram.graphql.generated.fragment.EpisodeFragment
import com.vkondrav.ram.room.FavoriteEpisode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

data class RamEpisode(
    val id: String,
    val title: String,
    val airDate: String?,
    val isFavorite: Flow<Boolean>,
) {

    object SourceConstructor {

        @Throws(InvalidDataException::class)
        operator fun invoke(fragment: EpisodeFragment, favorites: Flow<Set<String>>) =
            RamEpisode(
                id = fragment.id ?: throw InvalidDataException("missing id"),
                title = fragment.name ?: throw InvalidDataException("missing name"),
                airDate = fragment.air_date,
                isFavorite = favorites.map { it.contains(fragment.id) }.distinctUntilChanged(),
            )

        @Throws(InvalidDataException::class)
        operator fun invoke(favoriteEpisode: FavoriteEpisode, favorites: Flow<Set<String>>) =
            with(favoriteEpisode) {
                RamEpisode(
                    id = id,
                    title = title,
                    airDate = airDate,
                    isFavorite = favorites.map { it.contains(id) }.distinctUntilChanged(),
                )
            }
    }
}
