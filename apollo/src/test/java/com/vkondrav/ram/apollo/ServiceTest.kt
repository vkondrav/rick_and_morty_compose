package com.vkondrav.ram.apollo

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import org.junit.Before
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.mockito.kotlin.mock

class ServiceTest : BaseTest() {
    private lateinit var mockServer: MockServer
    private lateinit var client: Client
    private lateinit var subject: Service

    @Before
    fun setUp() = runTest {
        mockServer = MockServer()
        client = Client(mockServer.url(), MemoryCacheFactory(1_000))
        subject = Service(client.build())
    }

    @After
    fun tearDown() = runTest {
        mockServer.stop()
    }

    @Test
    fun `verify basic test`() = runTest {
        mockServer.enqueue(
            MockResponse(
                body = """
                { "data: { "id": "id_1" } }
                """.trimIndent()
            )
        )

        subject.query(FakeQuery("id_1")).data?.id shouldBe "id_1"
    }

    private data class FakeQuery(val id: String) : Query<FakeQuery.Data> {

        data class Data(
            val id: String?,
        ) : Query.Data

        override fun adapter(): Adapter<Data> {
            TODO("Not yet implemented")
        }

        override fun document(): String = "fake_query_document"

        override fun id(): String = "fake_query_id"

        override fun name(): String = "fake_query_name"

        override fun rootField(): CompiledField {
            return mock()
        }

        override fun serializeVariables(
            writer: JsonWriter,
            customScalarAdapters: CustomScalarAdapters
        ) {

        }
    }
}