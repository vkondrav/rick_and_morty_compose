package com.vkondrav.ram.room

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FavoriteEpisodesDaoTest : BaseDatabaseTest() {

    private lateinit var subject: FavoriteEpisodesDao

    override fun setUp() {
        super.setUp()
        subject = db.favoriteEpisodeDao()
    }

    @Test
    fun `verify inserting favorite episodes results in getAll flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteEpisode(
                id = "id_$id",
                title = "title_$id",
                airDate = "air_date_$id",
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
    fun `verify inserting favorite episodes results in getIds flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteEpisode(
                id = "id_$id",
                title = "title_$id",
                airDate = "air_date_$id",
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
    fun `verify deleting favorite episodes results in getAll flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteEpisode(
                id = "id_$id",
                title = "title_$id",
                airDate = "air_date_$id",
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
    fun `verify deleting favorite episodes results in getIds flow responses`() = runTest {
        val items = (0 until 3).map { id ->
            FavoriteEpisode(
                id = "id_$id",
                title = "title_$id",
                airDate = "air_date_$id",
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
