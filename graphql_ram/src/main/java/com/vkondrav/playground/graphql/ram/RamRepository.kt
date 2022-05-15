package com.vkondrav.playground.graphql.ram

import android.util.Log
import com.vkondrav.apollo.Service
import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.graphql.ram.LocationsQuery
import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.domain.RamLocation
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

interface RamRepository {
    suspend fun fetchCharacters(page: Int): List<RamCharacter>
    suspend fun fetchCharacterDetails(id: String): RamCharacterDetails
    suspend fun fetchLocations(): List<RamLocation>
}

internal class RamRepositoryImp(private val service: Service) : RamRepository {

    override suspend fun fetchCharacters(page: Int): List<RamCharacter> {
        val query = CharactersQuery(page)
        return service.query(query)
            .data
            ?.characters
            ?.results
            ?.filterNotNull()
            ?.mapNotNull { result ->

                runCatching {
                    RamCharacter(result.characterFragment)
                }.onFailure {
                    Log.e("RamRepositoryImpl", it.message!!)
                }.getOrNull()

            } ?: throw InvalidDataException("No results for ${query.name()} query")
    }

    override suspend fun fetchCharacterDetails(id: String): RamCharacterDetails {
        val query = CharacterDetailsQuery(id)
        return service.query(query)
            .data
            ?.character
            ?.let { character ->

                runCatching {
                    RamCharacterDetails(character)
                }.onFailure {
                    Log.e("RamRepositoryImpl", it.message!!)
                }.getOrNull()

            }
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    override suspend fun fetchLocations(): List<RamLocation> {
        val query = LocationsQuery()
        return service.query(query)
            .data
            ?.locations
            ?.results
            ?.filterNotNull()
            ?.mapNotNull {

                runCatching {
                    RamLocation(it.locationFragment)
                }.onFailure {
                    Log.e("RamRepositoryImpl", it.message!!)
                }.getOrNull()

            } ?: throw InvalidDataException("No result for ${query.name()} query")
    }
}