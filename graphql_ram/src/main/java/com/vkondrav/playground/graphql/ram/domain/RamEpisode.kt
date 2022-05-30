package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamEpisode(
    val id: String,
    val title: String,
    val airDate: String?,
) {

    @Throws(InvalidDataException::class)
    internal constructor(fragment: EpisodeFragment): this(
        id = fragment.id ?: throw InvalidDataException("missing id"),
        title = fragment.name ?: throw InvalidDataException("missing name"),
        airDate = fragment.air_date,
    )

}
