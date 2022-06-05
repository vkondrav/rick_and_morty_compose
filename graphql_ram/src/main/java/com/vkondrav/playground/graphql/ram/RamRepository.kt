package com.vkondrav.playground.graphql.ram

import com.apollographql.apollo3.exception.ApolloException
import com.vkondrav.apollo.Service
import com.vkondrav.apollo.dataOrThrow
import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.graphql.ram.EpisodesQuery
import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.graphql.ram.LocationsQuery
import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.graphql.ram.fragment.InfoFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

data class CharactersResponse(
    val info: InfoFragment,
    val characters: List<CharacterFragment>,
)

interface RamRepository {
    suspend fun fetchCharacters(page: Int): CharactersResponse
    suspend fun fetchCharacterDetails(id: String): CharacterDetailsQuery.Character
    suspend fun fetchLocations(page: Int): List<LocationsQuery.Result>
    suspend fun fetchLocationDetails(id: String): LocationDetailsQuery.Location
    suspend fun fetchEpisodes(page: Int): List<EpisodesQuery.Result>
    suspend fun fetchEpisodeDetails(id: String): EpisodeDetailsQuery.Episode
}

internal class RamRepositoryImp(private val service: Service) : RamRepository {

    @Throws(ApolloException::class)
    override suspend fun fetchCharacters(page: Int): CharactersResponse {
        val query = CharactersQuery(page)
        val response = service.query(query)
            .dataOrThrow
            .characters

        val characters = response
            ?.results
            ?.filterNotNull()
            ?.map { it.characterFragment }
            ?: throw ApolloException("No results for ${query.name()} query")

        val infoFragment = response.info?.infoFragment
            ?: throw ApolloException("No info for ${query.name()} query")

        return CharactersResponse(
            info = infoFragment,
            characters = characters,
        )
    }

    @Throws(ApolloException::class)
    override suspend fun fetchCharacterDetails(id: String): CharacterDetailsQuery.Character {
        val query = CharacterDetailsQuery(id)
        return service.query(query)
            .dataOrThrow
            .character
            ?: throw ApolloException("No result for ${query.name()} query")
    }

    @Throws(ApolloException::class)
    override suspend fun fetchLocations(page: Int): List<LocationsQuery.Result> {
        val query = LocationsQuery()
        return service.query(query)
            .dataOrThrow
            .locations
            ?.results
            ?.filterNotNull()
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    @Throws(ApolloException::class)
    override suspend fun fetchLocationDetails(id: String): LocationDetailsQuery.Location {
        val query = LocationDetailsQuery(id)
        return service.query(query)
            .dataOrThrow
            .location
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    @Throws(ApolloException::class)
    override suspend fun fetchEpisodes(page: Int): List<EpisodesQuery.Result> {
        val query = EpisodesQuery(page)
        return service.query(query)
            .dataOrThrow
            .episodes
            ?.results
            ?.filterNotNull()
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }

    @Throws(ApolloException::class)
    override suspend fun fetchEpisodeDetails(id: String): EpisodeDetailsQuery.Episode {
        val query = EpisodeDetailsQuery(id)
        return service.query(query)
            .dataOrThrow
            .episode
            ?: throw InvalidDataException("No result for ${query.name()} query")
    }
}
