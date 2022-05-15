package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class RamCharacterDetails(
    val character: RamCharacter,
    val origin: RamLocation?,
    val location: RamLocation?,
) {

    @Throws(InvalidDataException::class)
    internal constructor(character: CharacterDetailsQuery.Character) : this(
        character = RamCharacter(character.characterFragment),
        origin = character.origin?.locationFragment?.let { RamLocation(it) },
        location = character.location?.locationFragment?.let { RamLocation(it) }
    )

}