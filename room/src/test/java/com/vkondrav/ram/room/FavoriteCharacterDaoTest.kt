package com.vkondrav.ram.room

import androidx.room.Room
import app.cash.turbine.test
import com.vkondrav.ram.test.BaseRobolectricTest
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class FavoriteCharacterDaoTest : BaseRobolectricTest() {

    private lateinit var favoriteCharactersDao: FavoriteCharactersDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java,
        ).build()
        favoriteCharactersDao = db.favoriteCharactersDao()
    }

    @After
    fun tearDown() {
        db.close()
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

        favoriteCharactersDao.getAll().test {
            awaitItem() shouldBe emptyList()

            favoriteCharactersDao.insert(items[0])
            awaitItem() shouldBe listOf(items[0])

            favoriteCharactersDao.insert(items[1])
            awaitItem() shouldBe listOf(items[0], items[1])

            favoriteCharactersDao.insert(items[2])
            awaitItem() shouldBe listOf(items[0], items[1], items[2])

            cancel()
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

        favoriteCharactersDao.getIds().test {
            awaitItem() shouldBe emptyList()

            favoriteCharactersDao.insert(items[0])
            awaitItem() shouldBe listOf("id_0")

            favoriteCharactersDao.insert(items[1])
            awaitItem() shouldBe listOf("id_0", "id_1")

            favoriteCharactersDao.insert(items[2])
            awaitItem() shouldBe listOf("id_0", "id_1", "id_2")

            cancel()
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
        favoriteCharactersDao.insert(items[0])
        favoriteCharactersDao.insert(items[1])
        favoriteCharactersDao.insert(items[2])

        favoriteCharactersDao.getAll().test {
            awaitItem() shouldBe listOf(items[0], items[1], items[2])

            favoriteCharactersDao.delete(items[0].id) shouldBe 1
            awaitItem() shouldBe listOf(items[1], items[2])

            favoriteCharactersDao.delete(items[1].id) shouldBe 1
            awaitItem() shouldBe listOf(items[2])

            favoriteCharactersDao.delete(items[2].id) shouldBe 1
            awaitItem() shouldBe emptyList()

            favoriteCharactersDao.delete("garbage") shouldBe 0

            cancel()
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
        favoriteCharactersDao.insert(items[0])
        favoriteCharactersDao.insert(items[1])
        favoriteCharactersDao.insert(items[2])

        favoriteCharactersDao.getIds().test {
            awaitItem() shouldBe listOf("id_0", "id_1", "id_2")

            favoriteCharactersDao.delete(items[0].id) shouldBe 1
            awaitItem() shouldBe listOf("id_1", "id_2")

            favoriteCharactersDao.delete(items[1].id) shouldBe 1
            awaitItem() shouldBe listOf("id_2")

            favoriteCharactersDao.delete(items[2].id) shouldBe 1
            awaitItem() shouldBe emptyList()

            favoriteCharactersDao.delete("garbage") shouldBe 0

            cancel()
        }
    }

}
