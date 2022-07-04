package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.error.InvalidDataException
import com.vkondrav.ram.graphql.generated.LocationDetailsQuery
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamLocationDetails(
    val location: RamLocation,
    val characters: List<RamCharacter>,
) {

    class Adapter(
        private val characterAdapter: RamCharacter.Adapter,
        private val locationAdapter: RamLocation.Adapter,
    ) {
        @Throws(InvalidDataException::class)
        operator fun invoke(
            location: LocationDetailsQuery.Location,
            favoriteLocation: Flow<Set<String>>,
            favoriteCharacters: Flow<Set<String>>,
        ) = RamLocationDetails(
            location = locationAdapter(location.locationFragment, favoriteLocation),
            characters = location.residents.asSequence().filterNotNull().mapNotNull {
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
