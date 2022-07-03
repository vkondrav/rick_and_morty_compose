package com.vkondrav.ram.apollo

import app.cash.turbine.test
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import com.vkondrav.ram.test.BaseTest
import com.vkondrav.ram.util.TargetWrapper
import io.kotest.matchers.shouldBe
import org.junit.Before
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

/**
 * these test are written with fake graphql objects so this test is never affected by
 * any changes to actual queries/fragments
 */
class ServiceTest : BaseTest() {

    private lateinit var mockServer: MockServer
    private lateinit var client: Client
    private lateinit var subject: Service

    @Before
    fun setUp() = runTest {
        mockServer = MockServer()
        client = Client(mockServer.url(), MemoryCacheFactory(1_000), TargetWrapper())
        subject = Service(client.build())
    }

    @After
    fun tearDown() = runTest {
        mockServer.stop()
    }

    @Test
    fun `verify a basic query is executed with the correct value parsed`() = runTest {
        mockServer.enqueue(
            MockResponse(
                body =
                """
                {
                  "data": {
                    "fake_node": {
                      "fake_object": {
                        "fake_value": 117
                      }
                    }
                  }
                }
                """,
            ),
        )

        subject.query(FakeQuery()).dataOrThrow.fakeNode?.fakeObject?.fakeValue shouldBe 117
    }

    @Test
    fun `verify a basic flow query is executed with the correct value parsed`() = runTest {
        mockServer.enqueue(
            MockResponse(
                body =
                """
                {
                  "data": {
                    "fake_node": {
                      "fake_object": {
                        "fake_value": 117
                      }
                    }
                  }
                }
                """,
            ),
        )

        subject.queryAsFlow(FakeQuery()).test {
            awaitItem().dataOrThrow.fakeNode?.fakeObject?.fakeValue shouldBe 117
            awaitComplete()
        }
    }
}

