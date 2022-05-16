package com.vkondrav.playground.graphql.ram.domain

import android.util.Log
import com.vkondrav.graphql.ram.LocationDetailsQuery

data class RamLocationDetails(
    val location: RamLocation,
    val residents: List<RamCharacter>,
) {
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