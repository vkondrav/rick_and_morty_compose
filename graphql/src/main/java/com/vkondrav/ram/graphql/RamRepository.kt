package com.vkondrav.ram.graphql

import com.apollographql.apollo3.exception.ApolloException
import com.vkondrav.ram.apollo.Service
import com.vkondrav.ram.apollo.dataOrThrow
import com.vkondrav.graphql.ram.CharacterDetailsQuery
import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.graphql.ram.EpisodeDetailsQuery
import com.vkondrav.graphql.ram.EpisodesQuery
import com.vkondrav.graphql.ram.LocationDetailsQuery
import com.vkondrav.graphql.ram.LocationsQuery
import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.graphql.ram.fragment.EpisodeFragment
import com.vkondrav.graphql.ram.fragment.LocationFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class RamRepository(private val service: Service) {

    @Throws(ApolloException::class)
    suspend fun fetchCharacters(page: Int): PageResponse<CharacterFragment> {
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
    fun fetchCharacterDetails(id: String): Flow<CharacterDetailsQuery.Character>
        = service.queryAsFlow(CharacterDetailsQuery(id))
            .map { it.dataOrThrow.character }
            .filterNotNull()

    @Throws(ApolloException::class)
    suspend fun fetchLocations(page: Int): PageResponse<LocationFragment> {
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
    fun fetchLocationDetails(id: String): Flow<LocationDetailsQuery.Location>
        = service.queryAsFlow(LocationDetailsQuery(id))
            .map { it.dataOrThrow.location }
            .filterNotNull()

    @Throws(ApolloException::class)
    suspend fun fetchEpisodes(page: Int): PageResponse<EpisodeFragment> {
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
    fun fetchEpisodeDetails(id: String): Flow<EpisodeDetailsQuery.Episode>
        = service.queryAsFlow(EpisodeDetailsQuery(id))
            .map { it.dataOrThrow.episode }
            .filterNotNull()
}
