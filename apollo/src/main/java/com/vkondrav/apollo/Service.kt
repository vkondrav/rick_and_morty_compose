package com.vkondrav.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.executeCacheAndNetwork
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import kotlinx.coroutines.flow.Flow

interface Service {
    suspend fun <D : Query.Data> query(
        query: Query<D>,
        fetchPolicy: FetchPolicy = FetchPolicy.CacheAndNetwork,
    ): ApolloResponse<D>

    fun <D : Query.Data> queryAsFlow(
        query: Query<D>,
        fetchPolicy: FetchPolicy = FetchPolicy.CacheAndNetwork,
    ): Flow<ApolloResponse<D>>
}

internal class ServiceImpl(private val client: ApolloClient) : Service {
    override suspend fun <D : Query.Data> query(
        query: Query<D>,
        fetchPolicy: FetchPolicy,
    ) = client.query(query)
            .fetchPolicy(fetchPolicy)
            .execute()

    override fun <D : Query.Data> queryAsFlow(
        query: Query<D>,
        fetchPolicy: FetchPolicy,
    ): Flow<ApolloResponse<D>> = client.query(query)
        .fetchPolicy(fetchPolicy)
        .toFlow()
}
