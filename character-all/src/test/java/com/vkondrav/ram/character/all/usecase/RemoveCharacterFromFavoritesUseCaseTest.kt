package com.vkondrav.ram.character.all.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.snackbar.usecase.ShowSnackbarMessageUseCase
import com.vkondrav.ram.test.BaseTest
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class RemoveCharacterFromFavoritesUseCaseTest : BaseTest() {

    private val favoriteCharactersDao = mockk<FavoriteCharactersDao>(relaxed = true)
    private val showSnackbarMessageUseCase = mockk<ShowSnackbarMessageUseCase>(relaxed = true)
    private val testTree = mockk<Timber.Tree>(relaxed = true)

    private lateinit var subject: RemoveCharacterFromFavoritesUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        Timber.plant(testTree)
        subject = RemoveCharacterFromFavoritesUseCase(
            favoriteCharactersDao,
            showSnackbarMessageUseCase,
            Dispatchers.Unconfined,
        )
    }

    @After
    fun tearDown() {
        Timber.uproot(testTree)
    }

    @Test
    fun `verify use case`() {
        val character = mockk<RamCharacter> {
            every { id } returns "id_1"
            every { name } returns "name_1"
        }
        subject(character)
        coVerifySequence {
            favoriteCharactersDao.delete("id_1")
            showSnackbarMessageUseCase("name_1 removed from favorites")
        }
    }

    @Test
    fun `verify exception is handled`() {
        val character = mockk<RamCharacter> {
            every { id } returns "id_1"
        }
        val error = Exception("oops")
        coEvery { favoriteCharactersDao.delete("id_1") } throws error
        subject(character)
        verify { testTree.e(error) }
        verify { showSnackbarMessageUseCase wasNot Called }
    }
}
