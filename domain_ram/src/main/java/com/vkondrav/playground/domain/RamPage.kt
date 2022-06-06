package com.vkondrav.playground.domain

import com.vkondrav.playground.graphql.ram.CharactersResponse
import timber.log.Timber

data class RamPage<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>,
) {

    class SourceConstructor(
        private val characterSourceConstructor: RamCharacter.SourceConstructor,
    ) {
        operator fun invoke(response: CharactersResponse, favorites: Set<String>) =
            with(response) {
                RamPage(
                    info.prev,
                    info.next,
                    characters.mapNotNull { fragment ->
                        runCatching {
                           characterSourceConstructor(fragment, favorites)
                        }.onFailure {
                            Timber.e(it)
                        }.getOrNull()
                    },
                )
            }
    }
}
