package com.vkondrav.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import kotlinx.coroutines.flow.Flow

interface Service {
    suspend fun <D: Query.Data> query(query: Query<D>): ApolloResponse<D>
    fun <D: Query.Data> queryAsFlow(query: Query<D>): Flow<ApolloResponse<D>>
}

internal class ServiceImpl(private val client: ApolloClient): Service {
    override suspend fun <D : Query.Data> query(query: Query<D>)
        = client.query(query).execute()

    override fun <D : Query.Data> queryAsFlow(query: Query<D>): Flow<ApolloResponse<D>>
        = client.query(query).toFlow()
}
