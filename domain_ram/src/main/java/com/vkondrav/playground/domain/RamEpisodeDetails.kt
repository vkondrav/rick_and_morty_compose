package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamEpisodeDetails(
    val episode: RamEpisode,
    val characters: List<RamCharacter>,
) {

    class SourceTransformer(
        private val episodeSourceTransformer: RamEpisode.SourceTransformer,
        private val characterSourceTransformer: RamCharacter.SourceTransformer,
    ) {
        operator fun invoke(
            episode: EpisodeDetailsQuery.Episode,
            favoriteEpisodes: Set<String>,
            favoriteCharacters: Set<String>,
        ) = RamEpisodeDetails(
            episode = episodeSourceTransformer(episode.episodeFragment, favoriteEpisodes),
            characters = episode.characters.asSequence().filterNotNull().mapNotNull {
                try {
                    characterSourceTransformer(it.characterFragment, favoriteCharacters)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
