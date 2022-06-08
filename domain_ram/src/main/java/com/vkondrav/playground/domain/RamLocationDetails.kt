package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamLocationDetails(
    val location: RamLocation,
    val characters: List<RamCharacter>,
) {

    class SourceConstructor(
        private val characterSourceConstructor: RamCharacter.SourceConstructor,
        private val locationSourceConstructor: RamLocation.SourceConstructor,
    ) {
        @Throws(InvalidDataException::class)
        operator fun invoke(
            location: LocationDetailsQuery.Location,
            favoriteLocation: Flow<Set<String>>,
            favoriteCharacters: Flow<Set<String>>,
        ) = RamLocationDetails(
            location = locationSourceConstructor(location.locationFragment, favoriteLocation),
            characters = location.residents.asSequence().filterNotNull().mapNotNull {
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
