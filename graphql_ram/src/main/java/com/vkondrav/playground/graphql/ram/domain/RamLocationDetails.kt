package com.vkondrav.playground.graphql.ram.domain

import android.util.Log
import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamLocationDetails(
    val location: RamLocation,
    val residents: List<RamCharacter>,
) {
    @SuppressWarnings("TooGenericExceptionCaught")
    @Throws(InvalidDataException::class)
    internal constructor(location: LocationDetailsQuery.Location) : this(
        location = RamLocation(location.locationFragment),
        residents = location.residents.mapNotNull {
            try {
                RamCharacter(it!!.characterFragment)
            } catch (e: Throwable) {
                Log.e("", e.message!!)
                null
            }
        }
    )
}
