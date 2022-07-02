package com.vkondrav.ram.domain

import app.cash.turbine.test
import com.vkondrav.ram.graphql.error.InvalidDataException
import com.vkondrav.ram.graphql.generated.fragment.CharacterFragment
import com.vkondrav.ram.room.FavoriteCharacter
import com.vkondrav.ram.test.BaseTest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RamCharacterTest : BaseTest() {

    private lateinit var subject: RamCharacter.SourceConstructor

    @Before
    fun setUp() {
        subject = RamCharacter.SourceConstructor
    }

    @Test
    fun `verify constructor throws exception when character fragment has no id`() {
        shouldThrow<InvalidDataException> {
            subject(
                CharacterFragment(
                    id = null,
                    "name", "status", "species", "image",
                ),
                emptyFlow(),
            )
        }.message shouldBe "missing id"
    }

    @Test
    fun `verify constructor throws exception when character fragment has no name`() {
        shouldThrow<InvalidDataException> {
            subject(
                CharacterFragment(
                    "id",
                    name = null,
                    "status", "species", "image",
                ),
                emptyFlow(),
            )
        }.message shouldBe "missing name"
    }

    @Test
    fun `verify constructor creates a valid domain model from character fragment`() {
        subject(
            CharacterFragment(
                "id",
                "name",
                "status",
                "species",
                "image",
            ),
            emptyFlow(),
        ).run {
            id shouldBe "id"
            name shouldBe "name"
            status shouldBe "status"
            species shouldBe "species"
            image shouldBe "image"
        }
    }

    @Test
    fun `verify isFavorite flow maps the favorites flow based on id (character fragment)`() =
        runTest {
            subject(
                mockk<CharacterFragment>(relaxed = true) {
                    every { id } returns "id_1"
                },
                flowOf(
                    setOf("id_1"), //id matches
                    (1..100).setOfIds, //id matches (distinctUntilChanged filters out noise)
                    setOf("id_2"), //no match
                    (2..100).setOfIds, //no matches (distinctUntilChanged filters out noise)
                    setOf("id_2", "id_1"), //id matches
                ),
            ).run {
                isFavorite.test {
                    awaitItem() shouldBe true
                    awaitItem() shouldBe false
                    awaitItem() shouldBe true
                    awaitComplete()
                }
            }
        }

    @Test
    fun `verify constructor creates a valid domain model from favorite character`() {
        subject(
            FavoriteCharacter(
                "id",
                "name",
                "status",
                "species",
                "image",
            ),
            emptyFlow(),
        ).run {
            id shouldBe "id"
            name shouldBe "name"
            status shouldBe "status"
            species shouldBe "species"
            image shouldBe "image"
        }
    }

    @Test
    fun `verify isFavorite flow maps the favorites flow based on id (favorite character)`() =
        runTest {
            subject(
                mockk<FavoriteCharacter>(relaxed = true) {
                    every { id } returns "id_1"
                },
                flowOf(
                    setOf("id_1"), //id matches
                    (1..100).setOfIds, //id matches (distinctUntilChanged filters out noise)
                    setOf("id_2"), //no match
                    (2..100).setOfIds, //no matches (distinctUntilChanged filters out noise)
                    setOf("id_2", "id_1"), //id matches
                ),
            ).run {
                isFavorite.test {
                    awaitItem() shouldBe true
                    awaitItem() shouldBe false
                    awaitItem() shouldBe true
                    awaitComplete()
                }
            }
        }

    private val IntRange.setOfIds
        get() = map { "id_$it" }
            .toSet()
}
