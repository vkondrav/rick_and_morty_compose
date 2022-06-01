package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamEpisodeDetails(
    val episode: RamEpisode,
    val characters: List<RamCharacter>,
) {

    @Throws(InvalidDataException::class)
    constructor(episode: EpisodeDetailsQuery.Episode): this(
        episode = RamEpisode(episode.episodeFragment),
        characters = episode.characters.asSequence().filterNotNull().mapNotNull {
            try {
                RamCharacter(it.characterFragment, false)
            } catch (e: InvalidDataException) {
                Timber.e(e)
                null
            }
        }.toList(),
    )
}
