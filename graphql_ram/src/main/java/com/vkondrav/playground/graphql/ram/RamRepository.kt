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
import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException

interface RamRepository {
    suspend fun fetchCharacters(page: Int): PageResponse<CharacterFragment>
    suspend fun fetchCharacterDetails(id: String): CharacterDetailsQuery.Character
    suspend fun fetchLocations(page: Int): List<LocationsQuery.Result>
    suspend fun fetchLocationDetails(id: String): LocationDetailsQuery.Location
    suspend fun fetchEpisodes(page: Int): PageResponse<EpisodeFragment>
    suspend fun fetchEpisodeDetails(id: String): EpisodeDetailsQuery.Episode
}

internal class RamRepositoryImp(private val service: Service) : RamRepository {

    @Throws(ApolloException::class)
    override suspend fun fetchCharacters(page: Int): PageResponse<CharacterFragment> {
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

        return PageResponse(
            info = infoFragment,
            items = characters,
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

    override suspend fun fetchEpisodes(page: Int): PageResponse<EpisodeFragment> {
        val query = EpisodesQuery(page)

        val response = service.query(query)
            .dataOrThrow
            .episodes

        val characters = response
            ?.results
            ?.filterNotNull()
            ?.map { it.episodeFragment }
            ?: throw ApolloException("No results for ${query.name()} query")

        val infoFragment = response.info?.infoFragment
            ?: throw ApolloException("No info for ${query.name()} query")

        return PageResponse(
            info = infoFragment,
            items = characters,
        )
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
