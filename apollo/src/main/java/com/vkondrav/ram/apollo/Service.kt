package com.vkondrav.ram.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import kotlinx.coroutines.flow.Flow

class Service(private val client: ApolloClient) {

    suspend fun <D : Query.Data> query(
        query: Query<D>,
        fetchPolicy: FetchPolicy = FetchPolicy.CacheAndNetwork,
    ) = client.query(query)
        .fetchPolicy(fetchPolicy)
        .execute()

    fun <D : Query.Data> queryAsFlow(
        query: Query<D>,
        fetchPolicy: FetchPolicy = FetchPolicy.CacheAndNetwork,
    ): Flow<ApolloResponse<D>> = client.query(query)
        .fetchPolicy(fetchPolicy)
        .toFlow()
}
