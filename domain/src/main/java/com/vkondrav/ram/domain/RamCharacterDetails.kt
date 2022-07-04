package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.error.InvalidDataException
import com.vkondrav.ram.graphql.generated.CharacterDetailsQuery
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamCharacterDetails(
    val character: RamCharacter,
    val origin: RamLocation?,
    val location: RamLocation?,
    val episodes: List<RamEpisode>,
) {

    class Adapter(
        private val characterAdapter: RamCharacter.Adapter,
        private val episodeAdapter: RamEpisode.Adapter,
        private val locationAdapter: RamLocation.Adapter,
    ) {

        @Throws(InvalidDataException::class)
        operator fun invoke(
            character: CharacterDetailsQuery.Character,
            favoriteCharacters: Flow<Set<String>>,
            favoriteEpisodes: Flow<Set<String>>,
            favoriteLocations: Flow<Set<String>>,
        ) = RamCharacterDetails(
            character = characterAdapter(character.characterFragment, favoriteCharacters),
            origin = character.origin?.locationFragment?.let {
                locationAdapter(
                    it,
                    favoriteLocations,
                )
            },
            location = character.location?.locationFragment?.let {
                locationAdapter(
                    it,
                    favoriteLocations,
                )
            },
            episodes = character.episode.asSequence().filterNotNull().mapNotNull {
                try {
                    episodeAdapter(it.episodeFragment, favoriteEpisodes)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
