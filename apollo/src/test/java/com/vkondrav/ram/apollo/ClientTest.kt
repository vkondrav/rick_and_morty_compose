package com.vkondrav.ram.apollo

import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.vkondrav.ram.test.BaseTest
import org.junit.Before
import org.junit.Test

class ClientTest: BaseTest() {

    private lateinit var subject: Client

    @Before
    fun setUp() {
        subject = Client("server_url", MemoryCacheFactory(1_000))
    }

    @Test
    fun `verify the client is built with the correct attributes`() {
        with(subject.build()) {
        }
    }
}
