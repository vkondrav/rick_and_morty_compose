package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import kotlinx.coroutines.flow.Flow
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
                isFavorite = favorites.map { it.contains(fragment.id) },
            )
    }
}
