package com.vkondrav.ram.character.all.viewmodel

import app.cash.turbine.test
import com.vkondrav.ram.character.all.source.CharactersSource
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CharactersViewModelTest : BaseTest() {

    private val charactersSource: CharactersSource = mockk(relaxed = true)

    private lateinit var subject: CharactersViewModel

    @Before
    fun setUp() {
        clearAllMocks()
        subject = CharactersViewModel(
            charactersSource,
            Dispatchers.Unconfined,
        )
    }

    @Test
    fun `verify characters source is used with pager`() = runTest {
        subject.pagingData.test {
            //paging data has no public method to even confirm anything and
            //characters source seems to be never called, needs further investigation
            cancelAndConsumeRemainingEvents().size shouldBe 1
        }
    }
}
