package com.vkondrav.playground.graphql.ram.domain

import android.util.Log
import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamEpisodeDetails(
    val episode: RamEpisode,
    val characters: List<RamCharacter>,
) {

    @SuppressWarnings("TooGenericExceptionCaught")
    @Throws(InvalidDataException::class)
    internal constructor(episode: EpisodeDetailsQuery.Episode): this(
        episode = RamEpisode(episode.episodeFragment),
        characters = episode.characters.mapNotNull {
            try {
                RamCharacter(it!!.characterFragment)
            } catch (e: Throwable) {
                Log.e("", e.message!!)
                null
            }
        }
    )
}
