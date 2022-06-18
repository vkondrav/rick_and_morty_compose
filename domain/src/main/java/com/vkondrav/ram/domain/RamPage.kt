package com.vkondrav.ram.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.ram.graphql.PageResponse
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamPage<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>,
) {

    class SourceConstructor(
        private val characterSourceConstructor: RamCharacter.SourceConstructor,
        private val episodeSourceConstructor: RamEpisode.SourceConstructor,
        private val locationSourceConstructor: RamLocation.SourceConstructor,
    ) {

        fun characters(response: PageResponse<CharacterFragment>, favorites: Flow<Set<String>>) =
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

        fun episodes(response: PageResponse<EpisodeFragment>, favorites: Flow<Set<String>>) =
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

        fun locations(response: PageResponse<LocationFragment>, favorites: Flow<Set<String>>) =
            with(response) {
                RamPage(
                    info.prev,
                    info.next,
                    items.mapNotNull { fragment ->
                        runCatching {
                            locationSourceConstructor(fragment, favorites)
                        }.onFailure {
                            Timber.e(it)
                        }.getOrNull()
                    },
                )
            }
    }
}
