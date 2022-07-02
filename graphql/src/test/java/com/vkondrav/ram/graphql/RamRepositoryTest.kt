package com.vkondrav.ram.graphql

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.api.Error as ApolloError
import com.vkondrav.ram.apollo.Service
import com.vkondrav.ram.graphql.generated.CharactersQuery
import com.vkondrav.ram.graphql.generated.fragment.CharacterFragment
import com.vkondrav.ram.graphql.generated.fragment.InfoFragment
import com.vkondrav.ram.graphql.generated.test.CharactersQuery_TestBuilder.Data
import com.vkondrav.ram.test.BaseTest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RamRepositoryTest : BaseTest() {

    private val service: Service = mockk(relaxed = true)
    private lateinit var subject: RamRepository

    @Before
    fun setUp() {
        clearAllMocks()
        subject = RamRepository(service)
    }

    @Test
    fun `verify characters query with errors throws exception`() = runTest {
        val errors = listOf(
            ApolloError(
                "lol error",
                null, null, null, null,
            ),
        )

        coEvery { service.query(CharactersQuery(0)) } returns ApolloResponse.Builder(
            mockk(),
            mockk(),
            data = CharactersQuery.Data { },
        ).errors(errors).build()

        shouldThrow<ApolloException> {
            subject.fetchCharacters(0)
        } shouldBe ApolloException("The response has errors: $errors")
    }

    @Test
    fun `verify characters query with no results throws exception`() = runTest {
        val query = CharactersQuery(0)

        coEvery { service.query(query) } returns ApolloResponse.Builder(
            mockk(),
            mockk(),
            data = CharactersQuery.Data(
                CharactersQuery.Characters(
                    info = mockk(),
                    results = null,
                ),
            ),
        ).build()

        shouldThrow<ApolloException> {
            subject.fetchCharacters(0)
        } shouldBe ApolloException("No results for ${query.name()} query")
    }

    @Test
    fun `verify characters query with no info throws exception`() = runTest {
        val query = CharactersQuery(0)

        coEvery { service.query(query) } returns ApolloResponse.Builder(
            mockk(),
            mockk(),
            data = CharactersQuery.Data(
                CharactersQuery.Characters(
                    info = null,
                    results = emptyList(),
                ),
            ),

        ).build()

        shouldThrow<ApolloException> {
            subject.fetchCharacters(0)
        } shouldBe ApolloException("No info for ${query.name()} query")
    }

    @Test
    fun `verify characters query with valid data returns a page response`() = runTest {
        val infoFragment = mockk<InfoFragment>()
        val characterFragments = listOf<CharacterFragment>(
            mockk(),
            mockk(),
            mockk(),
            mockk(),
        )

        coEvery { service.query(CharactersQuery(0)) } returns ApolloResponse.Builder(
            mockk(),
            mockk(),
            data = CharactersQuery.Data(
                CharactersQuery.Characters(
                    info = CharactersQuery.Info("", infoFragment),
                    results = characterFragments.map {
                        CharactersQuery.Result("", it)
                    },
                ),
            ),
        ).build()

        subject.fetchCharacters(0) shouldBe PageResponse(
            infoFragment,
            characterFragments,
        )
    }
}
