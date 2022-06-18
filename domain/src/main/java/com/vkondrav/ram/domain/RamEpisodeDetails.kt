package com.vkondrav.ram.domain

import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.ram.graphql.error.InvalidDataException
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamEpisodeDetails(
    val episode: RamEpisode,
    val characters: List<RamCharacter>,
) {

    class SourceConstructor(
        private val episodeSourceConstructor: RamEpisode.SourceConstructor,
        private val characterSourceConstructor: RamCharacter.SourceConstructor,
    ) {
        operator fun invoke(
            episode: EpisodeDetailsQuery.Episode,
            favoriteEpisodes: Flow<Set<String>>,
            favoriteCharacters: Flow<Set<String>>,
        ) = RamEpisodeDetails(
            episode = episodeSourceConstructor(episode.episodeFragment, favoriteEpisodes),
            characters = episode.characters.asSequence().filterNotNull().mapNotNull {
                try {
                    characterSourceConstructor(it.characterFragment, favoriteCharacters)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
