package com.vkondrav.ram.domain

import com.vkondrav.ram.common.util.InvalidDataException
import com.vkondrav.ram.graphql.generated.LocationDetailsQuery
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

data class RamLocationDetails(
    val location: RamLocation,
    val characters: List<RamCharacter>,
) {

    class Factory(
        private val characterFactory: RamCharacter.Factory,
        private val locationFactory: RamLocation.Factory,
    ) {
        @Throws(InvalidDataException::class)
        operator fun invoke(
            location: LocationDetailsQuery.Location,
            favoriteLocation: Flow<Set<String>>,
            favoriteCharacters: Flow<Set<String>>,
        ) = RamLocationDetails(
            location = locationFactory(location.locationFragment, favoriteLocation),
            characters = location.residents.asSequence().filterNotNull().mapNotNull {
                try {
                    characterFactory(it.characterFragment, favoriteCharacters)
                } catch (e: InvalidDataException) {
                    Timber.e(e)
                    null
                }
            }.toList(),
        )
    }
}
