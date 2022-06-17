package com.vkondrav.ram.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache

class Client(
    private val serverUrl: String,
    private val cache: NormalizedCacheFactory,
) {
    fun build() = ApolloClient.Builder()
        .serverUrl(serverUrl)
        .normalizedCache(cache)
        .build()
}
