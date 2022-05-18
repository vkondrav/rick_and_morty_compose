package com.vkondrav.playground.graphql.ram

import android.util.Log
import com.vkondrav.apollo.Service
import com.vkondrav.graphql.ram.*
import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.graphql.ram.LocationsQuery
import com.vkondrav.playground.graphql.ram.domain.*
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

interface RamRepository {
    suspend fun fetchCharacters(page: Int): List<RamCharacter>
    suspend fun fetchCharacterDetails(id: String): RamCharacterDetails
    suspend fun fetchLocations(page: Int): List<RamLocation>
    suspend fun fetchLocationDetails(id: String): RamLocationDetails
    suspend fun fetchEpisodes(page: Int): List<RamEpisode>
    suspend fun fetchEpisodeDetails(id: String): RamEpisodeDetails
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

            }
            ?: throw InvalidDataException("No results for ${query.name()} query")
    }

    override suspend fun fetchCharacterDetails(id: String): RamCharacterDetails {
        val query = CharacterDetailsQuery(id)
        return service.query(query)
            .data
            ?.character
            ?.let { character ->
                RamCharacterDetails(character)
            }
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    override suspend fun fetchLocations(page: Int): List<RamLocation> {
        val query = LocationsQuery()
        return service.query(query)
            .data
            ?.locations
            ?.results
            ?.filterNotNull()
            ?.mapNotNull { result ->

                runCatching {
                    RamLocation(result.locationFragment)
                }.onFailure {
                    Log.e("RamRepositoryImpl", it.message!!)
                }.getOrNull()

            } ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    override suspend fun fetchLocationDetails(id: String): RamLocationDetails {
        val query = LocationDetailsQuery(id)
        return service.query(query)
            .data
            ?.location
            ?.let { location ->
                RamLocationDetails(location)
            }
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    override suspend fun fetchEpisodes(page: Int): List<RamEpisode> {
        val query = EpisodesQuery(page)
        return service.query(query)
            .data
            ?.episodes
            ?.results
            ?.mapNotNull {

                runCatching {
                    RamEpisode(it!!.episodeFragment)
                }.onFailure {
                    Log.e("RamRepositoryImpl", it.message!!)
                }.getOrNull()

            }
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    override suspend fun fetchEpisodeDetails(id: String): RamEpisodeDetails {
        val query = EpisodeDetailsQuery(id)
        return service.query(query)
            .data
            ?.episode
            ?.let { episode ->
                RamEpisodeDetails(episode)
            }
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }
}