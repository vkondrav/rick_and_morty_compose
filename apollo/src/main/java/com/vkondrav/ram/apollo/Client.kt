package com.vkondrav.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache

interface Client {
    fun build(): ApolloClient
}

internal class ClientImpl(
    private val serverUrl: String,
    private val cache: NormalizedCacheFactory,
) : Client {
    override fun build() = ApolloClient.Builder()
        .serverUrl(serverUrl)
        .normalizedCache(cache)
        .build()
}
