package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamCharacterDetails(
    val character: RamCharacter,
    val origin: RamLocation?,
    val location: RamLocation?,
    val episodes: List<RamEpisode>,
) {

    class SourceConstructor(
        private val characterSourceConstructor: RamCharacter.SourceConstructor,
        private val episodeSourceConstructor: RamEpisode.SourceConstructor,
        private val locationSourceConstructor: RamLocation.SourceConstructor,
    ) {

        @Throws(InvalidDataException::class)
        operator fun invoke(
            character: CharacterDetailsQuery.Character,
            favoriteCharacters: Set<String>,
            favoriteLocations: Set<String>,
            favoriteEpisodes: Set<String>,
        ) = RamCharacterDetails(
            character = characterSourceConstructor(character.characterFragment, favoriteCharacters),
            origin = character.origin?.locationFragment?.let {
                locationSourceConstructor(
                    it,
                    favoriteLocations,
                )
            },
            location = character.location?.locationFragment?.let {
                locationSourceConstructor(
                    it,
                    favoriteLocations,
                )
            },
            episodes = character.episode.asSequence().filterNotNull().mapNotNull {
                try {
                    episodeSourceConstructor(it.episodeFragment, favoriteEpisodes)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
