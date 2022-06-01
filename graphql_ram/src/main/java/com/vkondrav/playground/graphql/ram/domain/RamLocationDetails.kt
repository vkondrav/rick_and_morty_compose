package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

data class RamLocationDetails(
    val location: RamLocation,
    val residents: List<RamCharacter>,
) {
    @Throws(InvalidDataException::class)
    constructor(location: LocationDetailsQuery.Location) : this(
        location = RamLocation(location.locationFragment),
        residents = location.residents.asSequence().filterNotNull().mapNotNull {
            try {
                RamCharacter(it.characterFragment, emptySet())
            } catch (e: InvalidDataException) {
                Timber.e(e)
                null
            }
        }.toList(),
    )
}
