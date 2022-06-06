package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamEpisode(
    val id: String,
    val title: String,
    val airDate: String?,
    val favorite: Boolean,
) {

    object SourceTransformer {

        @Throws(InvalidDataException::class)
        operator fun invoke(fragment: EpisodeFragment, favorites: Set<String>) =
            RamEpisode(
                id = fragment.id ?: throw InvalidDataException("missing id"),
                title = fragment.name ?: throw InvalidDataException("missing name"),
                airDate = fragment.air_date,
                favorite = favorites.contains(fragment.id),
            )
    }
}
