package com.vkondrav.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query

interface Service {
    suspend fun <D: Query.Data> query(query: Query<D>): ApolloResponse<D>
}

internal class ServiceImpl(private val client: ApolloClient): Service {
    override suspend fun <D : Query.Data> query(query: Query<D>)
        = client.query(query).execute()
}
