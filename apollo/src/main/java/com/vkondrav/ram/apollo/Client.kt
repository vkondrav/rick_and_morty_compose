package com.vkondrav.ram.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.vkondrav.ram.util.TargetWrapper

class Client(
    private val serverUrl: String,
    private val cache: NormalizedCacheFactory,
    private val wrapper: TargetWrapper,
) {
    fun build() = wrapper(ApolloClient.Builder())
        .serverUrl(serverUrl)
        .normalizedCache(cache)
        .build()
}
