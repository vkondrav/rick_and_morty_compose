package com.vkondrav.ram.room

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FavoriteCharactersDaoTest : BaseDatabaseTest() {

    private lateinit var subject: FavoriteCharactersDao

    override fun setUp() {
        super.setUp()
        subject = db.favoriteCharactersDao()
    }

    @Test
    fun `verify inserting favorite characters results in getAll flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteCharacter(
                id = "id_$id",
                name = "name_$id",
                status = "status_$id",
                species = "species_$id",
                image = "image_$id",
            )
        }

        subject.getAll().test {
            awaitItem() shouldBe emptyList()

            subject.insert(items[0])
            awaitItem() shouldBe listOf(items[0])

            subject.insert(items[1])
            awaitItem() shouldBe listOf(items[0], items[1])

            subject.insert(items[2])
            awaitItem() shouldBe listOf(items[0], items[1], items[2])

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify inserting favorite characters results in getIds flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteCharacter(
                id = "id_$id",
                name = "name_$id",
                status = "status_$id",
                species = "species_$id",
                image = "image_$id",
            )
        }

        subject.getIds().test {
            awaitItem() shouldBe emptyList()

            subject.insert(items[0])
            awaitItem() shouldBe listOf("id_0")

            subject.insert(items[1])
            awaitItem() shouldBe listOf("id_0", "id_1")

            subject.insert(items[2])
            awaitItem() shouldBe listOf("id_0", "id_1", "id_2")

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify deleting favorite characters results in getAll flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteCharacter(
                id = "id_$id",
                name = "name_$id",
                status = "status_$id",
                species = "species_$id",
                image = "image_$id",
            )
        }
        subject.insert(items[0])
        subject.insert(items[1])
        subject.insert(items[2])

        subject.getAll().test {
            awaitItem() shouldBe listOf(items[0], items[1], items[2])

            subject.delete(items[0].id) shouldBe 1
            awaitItem() shouldBe listOf(items[1], items[2])

            subject.delete(items[1].id) shouldBe 1
            awaitItem() shouldBe listOf(items[2])

            subject.delete(items[2].id) shouldBe 1
            awaitItem() shouldBe emptyList()

            subject.delete("garbage") shouldBe 0

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify deleting favorite characters results in getIds flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteCharacter(
                id = "id_$id",
                name = "name_$id",
                status = "status_$id",
                species = "species_$id",
                image = "image_$id",
            )
        }
        subject.insert(items[0])
        subject.insert(items[1])
        subject.insert(items[2])

        subject.getIds().test {
            awaitItem() shouldBe listOf("id_0", "id_1", "id_2")

            subject.delete(items[0].id) shouldBe 1
            awaitItem() shouldBe listOf("id_1", "id_2")

            subject.delete(items[1].id) shouldBe 1
            awaitItem() shouldBe listOf("id_2")

            subject.delete(items[2].id) shouldBe 1
            awaitItem() shouldBe emptyList()

            subject.delete("garbage") shouldBe 0

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }
}
