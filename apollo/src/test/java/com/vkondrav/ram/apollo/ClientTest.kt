package com.vkondrav.ram.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.vkondrav.ram.common.util.TargetWrapper
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ClientTest : BaseTest() {

    private val builder: ApolloClient.Builder = mockk(relaxed = true)

    private lateinit var subject: Client

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `verify the client is built with the correct attributes`() {
        mockkStatic("com.apollographql.apollo3.cache.normalized.NormalizedCache")

        every { builder.serverUrl("server_url") } returns builder

        val cache = MemoryCacheFactory(1_000)
        every { builder.normalizedCache(cache) } returns builder

        subject = Client("server_url", cache, TestWrapper(builder))

        subject.build()
        verify { builder.build() }
    }

    private class TestWrapper(private val builder: ApolloClient.Builder) : TargetWrapper() {
        override fun <T> invoke(target: T): T = builder as T
    }
}
