package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.PageResponse
import com.vkondrav.ram.graphql.generated.fragment.CharacterFragment
import com.vkondrav.ram.graphql.generated.fragment.EpisodeFragment
import com.vkondrav.ram.graphql.generated.fragment.LocationFragment
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamPage<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>,
) {

    class Factory(
        private val characterFactory: RamCharacter.Factory,
        private val episodeFactory: RamEpisode.Factory,
        private val locationFactory: RamLocation.Factory,
    ) {

        fun characters(response: PageResponse<CharacterFragment>, favorites: Flow<Set<String>>) =
            with(response) {
                RamPage(
                    info.prev,
                    info.next,
                    items.mapNotNull { fragment ->
                        runCatching {
                            characterFactory(fragment, favorites)
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
                            episodeFactory(fragment, favorites)
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
                            locationFactory(fragment, favorites)
                        }.onFailure {
                            Timber.e(it)
                        }.getOrNull()
                    },
                )
            }
    }
}
