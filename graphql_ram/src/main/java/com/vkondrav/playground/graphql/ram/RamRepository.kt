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
import com.vkondrav.graphql.ram.fragment.LocationFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

interface RamRepository {
    suspend fun fetchCharacters(page: Int): PageResponse<CharacterFragment>
    suspend fun fetchCharacterDetails(id: String): CharacterDetailsQuery.Character
    fun fetchCharacterDetailsF(id: String): Flow<CharacterDetailsQuery.Character>
    suspend fun fetchLocations(page: Int): PageResponse<LocationFragment>
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

    override fun fetchCharacterDetailsF(id: String): Flow<CharacterDetailsQuery.Character> {
        val query = CharacterDetailsQuery(id)
        return service.queryAsFlow(query)
            .map { it.dataOrThrow.character }
            .filterNotNull()
    }

    @Throws(ApolloException::class)
    override suspend fun fetchLocations(page: Int): PageResponse<LocationFragment> {
        val query = LocationsQuery(page)
        val response = service.query(query)
            .dataOrThrow
            .locations

        val locations = response
            ?.results
            ?.filterNotNull()
            ?.map { it.locationFragment }
            ?: throw ApolloException("No results for ${query.name()} query")

        val infoFragment = response.info?.infoFragment
            ?: throw ApolloException("No info for ${query.name()} query")

        return PageResponse(
            info = infoFragment,
            items = locations,
        )
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

        val episodes = response
            ?.results
            ?.filterNotNull()
            ?.map { it.episodeFragment }
            ?: throw ApolloException("No results for ${query.name()} query")

        val infoFragment = response.info?.infoFragment
            ?: throw ApolloException("No info for ${query.name()} query")

        return PageResponse(
            info = infoFragment,
            items = episodes,
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
