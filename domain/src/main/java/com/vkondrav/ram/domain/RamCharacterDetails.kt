package com.vkondrav.ram.domain

import com.vkondrav.ram.common.util.InvalidDataException
import com.vkondrav.ram.graphql.generated.CharacterDetailsQuery
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamCharacterDetails(
    val character: RamCharacter,
    val origin: RamLocation?,
    val location: RamLocation?,
    val episodes: List<RamEpisode>,
) {

    class Factory(
        private val characterFactory: RamCharacter.Factory,
        private val episodeFactory: RamEpisode.Factory,
        private val locationFactory: RamLocation.Factory,
    ) {

        @Throws(InvalidDataException::class)
        operator fun invoke(
            character: CharacterDetailsQuery.Character,
            favoriteCharacters: Flow<Set<String>>,
            favoriteEpisodes: Flow<Set<String>>,
            favoriteLocations: Flow<Set<String>>,
        ) = RamCharacterDetails(
            character = characterFactory(character.characterFragment, favoriteCharacters),
            origin = character.origin?.locationFragment?.let {
                locationFactory(
                    it,
                    favoriteLocations,
                )
            },
            location = character.location?.locationFragment?.let {
                locationFactory(
                    it,
                    favoriteLocations,
                )
            },
            episodes = character.episode.asSequence().filterNotNull().mapNotNull {
                try {
                    episodeFactory(it.episodeFragment, favoriteEpisodes)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
