package com.vkondrav.ram.apollo

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.vkondrav.ram.test.BaseTest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class UtilsTest : BaseTest() {

    @Test
    fun `verify dataOrThrow returns data when there are no errors`() {
        val fakeData = mockk<FakeQuery.Data>()
        val fakeResponse = mockk<ApolloResponse<FakeQuery.Data>>()

        every { fakeResponse.dataAssertNoErrors } returns fakeData

        fakeResponse.dataOrThrow shouldBe fakeData
    }

    @Test
    fun `verify dataOrThrow returns an exception when there are errors`() {
        val errorCause = Throwable("cause")
        val error = Throwable("oops", errorCause)
        val fakeResponse = mockk<ApolloResponse<FakeQuery.Data>>()

        every { fakeResponse.dataAssertNoErrors } throws error

        shouldThrow<ApolloException> {
            fakeResponse.dataOrThrow
        }.run {
            message shouldBe "oops"
            cause shouldBe errorCause
        }
    }
}
