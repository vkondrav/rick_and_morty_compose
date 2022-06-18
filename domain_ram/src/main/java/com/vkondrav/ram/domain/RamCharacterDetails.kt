package com.vkondrav.ram.domain

import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.ram.graphql.error.InvalidDataException
import kotlinx.coroutines.flow.Flow
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
            favoriteCharacters: Flow<Set<String>>,
            favoriteEpisodes: Flow<Set<String>>,
            favoriteLocations: Flow<Set<String>>,
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
