package com.vkondrav.ram.domain

import com.vkondrav.ram.graphql.PageResponse
import com.vkondrav.ram.graphql.generated.fragment.CharacterFragment
import com.vkondrav.ram.graphql.generated.fragment.InfoFragment
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.flow.Flow
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class RamPageTest : BaseTest() {

    private val characterFactory = mockk<RamCharacter.Factory>()
    private val episodeFactory = mockk<RamEpisode.Factory>()
    private val locationFactory = mockk<RamLocation.Factory>()
    private val testTree = mockk<Timber.Tree>(relaxed = true)

    private lateinit var subject: RamPage.Factory

    @Before
    fun setUp() {
        clearAllMocks()
        Timber.plant(testTree)
        subject = RamPage.Factory(
            characterFactory,
            episodeFactory,
            locationFactory,
        )
    }

    @After
    fun tearDown() {
        Timber.uproot(testTree)
    }

    @Test
    fun `verify page response is transformed into domain model`() {
        val c1 = mockk<CharacterFragment>()
        val c2 = mockk<CharacterFragment>()
        val c3 = mockk<CharacterFragment>()
        val c4 = mockk<CharacterFragment>()

        val favorites = mockk<Flow<Set<String>>>()

        val t1 = mockk<RamCharacter>()
        val t2 = mockk<RamCharacter>()
        val t3 = mockk<RamCharacter>()

        listOf(
            c1 to t1,
            c2 to t2,
            c3 to t3,
        ).forEach { (c, t) ->
            every { characterFactory(c, favorites) } returns t
        }

        val error = Exception("oops")
        every { characterFactory(c4, favorites) } throws error

        subject.characters(
            PageResponse(
                InfoFragment(
                    prev = 0,
                    next = 1,
                ),
                items = listOf(c1, c2, c3, c4),
            ),
            favorites,
        ).run {
            previousPage shouldBe 0
            nextPage shouldBe 1
            items shouldBe listOf(t1, t2, t3)
            verifySequence {
                characterFactory(c1, favorites)
                characterFactory(c2, favorites)
                characterFactory(c3, favorites)
                characterFactory(c4, favorites)
                testTree.e(error)
            }
        }
    }
}
