package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.error.InvalidDataException
import com.vkondrav.ram.graphql.generated.EpisodeDetailsQuery
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamEpisodeDetails(
    val episode: RamEpisode,
    val characters: List<RamCharacter>,
) {

    class Adapter(
        private val episodeAdapter: RamEpisode.Adapter,
        private val characterAdapter: RamCharacter.Adapter,
    ) {
        operator fun invoke(
            episode: EpisodeDetailsQuery.Episode,
            favoriteEpisodes: Flow<Set<String>>,
            favoriteCharacters: Flow<Set<String>>,
        ) = RamEpisodeDetails(
            episode = episodeAdapter(episode.episodeFragment, favoriteEpisodes),
            characters = episode.characters.asSequence().filterNotNull().mapNotNull {
                try {
                    characterAdapter(it.characterFragment, favoriteCharacters)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
