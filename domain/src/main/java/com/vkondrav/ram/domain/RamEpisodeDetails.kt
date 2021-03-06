package com.vkondrav.ram.domain

import com.vkondrav.ram.common.util.InvalidDataException
import com.vkondrav.ram.graphql.generated.EpisodeDetailsQuery
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamEpisodeDetails(
    val episode: RamEpisode,
    val characters: List<RamCharacter>,
) {

    class Factory(
        private val episodeFactory: RamEpisode.Factory,
        private val characterFactory: RamCharacter.Factory,
    ) {
        operator fun invoke(
            episode: EpisodeDetailsQuery.Episode,
            favoriteEpisodes: Flow<Set<String>>,
            favoriteCharacters: Flow<Set<String>>,
        ) = RamEpisodeDetails(
            episode = episodeFactory(episode.episodeFragment, favoriteEpisodes),
            characters = episode.characters.asSequence().filterNotNull().mapNotNull {
                try {
                    characterFactory(it.characterFragment, favoriteCharacters)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
