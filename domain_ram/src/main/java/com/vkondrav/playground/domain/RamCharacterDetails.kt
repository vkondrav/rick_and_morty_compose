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

    class SourceTransformer(
        private val characterSourceTransformer: RamCharacter.SourceTransformer,
        private val episodeSourceTransformer: RamEpisode.SourceTransformer,
        private val locationSourceTransformer: RamLocation.SourceTransformer,
    ) {

        @Throws(InvalidDataException::class)
        operator fun invoke(
            character: CharacterDetailsQuery.Character,
            favoriteCharacters: Set<String>,
            favoriteLocations: Set<String>,
            favoriteEpisodes: Set<String>,
        ) = RamCharacterDetails(
            character = characterSourceTransformer(character.characterFragment, favoriteCharacters),
            origin = character.origin?.locationFragment?.let {
                locationSourceTransformer(
                    it,
                    favoriteLocations,
                )
            },
            location = character.location?.locationFragment?.let {
                locationSourceTransformer(
                    it,
                    favoriteLocations,
                )
            },
            episodes = character.episode.asSequence().filterNotNull().mapNotNull {
                try {
                    episodeSourceTransformer(it.episodeFragment, favoriteEpisodes)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
