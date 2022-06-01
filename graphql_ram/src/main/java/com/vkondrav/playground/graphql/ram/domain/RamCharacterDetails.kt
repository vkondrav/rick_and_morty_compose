package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamCharacterDetails(
    val character: RamCharacter,
    val origin: RamLocation?,
    val location: RamLocation?,
    val episodes: List<RamEpisode>,
) {

    @Throws(InvalidDataException::class)
    constructor(character: CharacterDetailsQuery.Character, favorite: Boolean) : this(
        character = RamCharacter(character.characterFragment, favorite),
        origin = character.origin?.locationFragment?.let { RamLocation(it) },
        location = character.location?.locationFragment?.let { RamLocation(it) },
        episodes = character.episode.asSequence().filterNotNull().mapNotNull {
            try {
                RamEpisode(it.episodeFragment)
            } catch (e: InvalidDataException) {
                Timber.e(e)
                null
            }
        }.toList(),
    )

}
