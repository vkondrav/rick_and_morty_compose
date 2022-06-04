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
    constructor(
        character: CharacterDetailsQuery.Character,
        favoriteLocations: Set<String>,
        favoriteEpisodes: Set<String>,
    ) : this(
        character = RamCharacter(character.characterFragment, emptySet()),
        origin = character.origin?.locationFragment?.let { RamLocation(it, favoriteLocations) },
        location = character.location?.locationFragment?.let { RamLocation(it, favoriteLocations) },
        episodes = character.episode.asSequence().filterNotNull().mapNotNull {
            try {
                RamEpisode(it.episodeFragment, favoriteEpisodes)
            } catch (e: InvalidDataException) {
                Timber.e(e)
                null
            }
        }.toList(),
    )

}

object RamCharacterDetailsTransformer {
    operator fun invoke(
        character: CharacterDetailsQuery.Character,
        favoriteLocations: Set<String>,
        favoriteEpisodes: Set<String>,
    ) = RamCharacterDetails(character, favoriteLocations, favoriteEpisodes)
}
