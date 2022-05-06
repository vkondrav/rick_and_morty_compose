package com.vkondrav.apollo

import com.apollographql.apollo3.ApolloClient

interface Client {
    fun build(): ApolloClient
}

internal class ClientImpl(private val serverUrl: String): Client {
    override fun build() = ApolloClient.Builder()
        .serverUrl(serverUrl)
        .build()
}