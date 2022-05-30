package com.vkondrav.playground.graphql.ram

import com.vkondrav.apollo.Service
import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.graphql.ram.EpisodesQuery
import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.graphql.ram.LocationsQuery
import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.domain.RamEpisode
import com.vkondrav.playground.graphql.ram.domain.RamEpisodeDetails
import com.vkondrav.playground.graphql.ram.domain.RamLocation
import com.vkondrav.playground.graphql.ram.domain.RamLocationDetails
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import timber.log.Timber

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
                    Timber.e(it.message)
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
                    Timber.e(it)
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
                    Timber.e(it)
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
