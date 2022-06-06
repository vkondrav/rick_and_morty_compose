package com.vkondrav.playground.domain

import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamLocationDetails(
    val location: RamLocation,
    val residents: List<RamCharacter>,
) {

    class SourceTransformer(
        private val characterSourceTransformer: RamCharacter.SourceTransformer,
        private val locationSourceTransformer: RamLocation.SourceTransformer,
    ) {
        @Throws(InvalidDataException::class)
        operator fun invoke(
            location: LocationDetailsQuery.Location,
            favoriteLocation: Set<String>,
            favoriteCharacters: Set<String>,
        ) = RamLocationDetails(
            location = locationSourceTransformer(location.locationFragment, favoriteLocation),
            residents = location.residents.asSequence().filterNotNull().mapNotNull {
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
