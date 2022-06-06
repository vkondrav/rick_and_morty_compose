package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.playground.graphql.ram.PageResponse
import timber.log.Timber

data class RamPage<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>,
) {

    class SourceConstructor(
        private val characterSourceConstructor: RamCharacter.SourceConstructor,
        private val episodeSourceConstructor: RamEpisode.SourceConstructor,
    ) {

        fun characters(response: PageResponse<CharacterFragment>, favorites: Set<String>) =
            with(response) {
                RamPage(
                    info.prev,
                    info.next,
                    items.mapNotNull { fragment ->
                        runCatching {
                           characterSourceConstructor(fragment, favorites)
                        }.onFailure {
                            Timber.e(it)
                        }.getOrNull()
                    },
                )
            }

        fun episodes(response: PageResponse<EpisodeFragment>, favorites: Set<String>) =
            with(response) {
                RamPage(
                    info.prev,
                    info.next,
                    items.mapNotNull { fragment ->
                        runCatching {
                            episodeSourceConstructor(fragment, favorites)
                        }.onFailure {
                            Timber.e(it)
                        }.getOrNull()
                    },
                )
            }
    }
}
