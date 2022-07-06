package com.vkondrav.ram.app.screen.characters.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.app.screen.characters.adapter.CharactersViewItemAdapter
import com.vkondrav.ram.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class CharactersSourceTest : BaseTest() {

    private val fetchCharactersUseCase: FetchCharactersUseCase = mockk(relaxed = true)
    private val charactersViewItemAdapter: CharactersViewItemAdapter = mockk(relaxed = true)

    private val testTree = mockk<Timber.Tree>(relaxed = true)
    private lateinit var subject: CharactersSource

    @Before
    fun setUp() {
        clearAllMocks()
        Timber.plant(testTree)
        subject = CharactersSource(fetchCharactersUseCase, charactersViewItemAdapter)
    }

    @After
    fun tearDown() {
        Timber.uproot(testTree)
    }

    @Test
    fun `verify the refresh key is the anchor position`() {
        val state = mockk<PagingState<Int, ComposableItem>> {
            every { anchorPosition } returns 1_000
        }
        subject.getRefreshKey(state) shouldBe 1_000
    }

    @Test
    fun `verify an exception is caught, recorded and load result error returned`() = runTest {
        val exception = Exception("oops")
        coEvery { fetchCharactersUseCase(1_000) } throws exception

        val loadParams = mockk<PagingSource.LoadParams<Int>> {
            every { key } returns 1_000
        }
        subject.load(loadParams) shouldBe PagingSource.LoadResult.Error(exception)
        verify { testTree.e(exception) }
    }

    @Test
    fun `verify successful page load result`() = runTest {
        val characters = listOf<RamCharacter>(
            mockk(),
            mockk(),
            mockk(),
        )

        coEvery { fetchCharactersUseCase(1) } returns Result.success(
            RamPage(
                previousPage = 0,
                nextPage = 2,
                items = characters,
            ),
        )

        val composables = listOf<ComposableItem>(
            mockk(),
            mockk(),
            mockk(),
        )

        every { charactersViewItemAdapter(characters) } returns composables

        val loadParams = mockk<PagingSource.LoadParams<Int>> {
            every { key } returns null //tests default page
        }

        subject.load(loadParams) shouldBe PagingSource.LoadResult.Page(
            data = composables,
            prevKey = 0,
            nextKey = 2,
        )
    }
}
