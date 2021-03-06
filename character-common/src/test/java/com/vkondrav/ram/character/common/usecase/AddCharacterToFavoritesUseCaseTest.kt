package com.vkondrav.ram.character.common.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.FavoriteCharacter
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.snackbar.usecase.ShowSnackbarMessageUseCase
import com.vkondrav.ram.test.BaseTest
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class AddCharacterToFavoritesUseCaseTest : BaseTest() {

    private val favoriteCharactersDao = mockk<FavoriteCharactersDao>(relaxed = true)
    private val showSnackbarMessageUseCase = mockk<ShowSnackbarMessageUseCase>(relaxed = true)
    private val adapter = mockk<RamCharacter.Adapter>()
    private val testTree = mockk<Timber.Tree>(relaxed = true)

    private lateinit var subject: AddCharacterToFavoritesUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        Timber.plant(testTree)
        subject = AddCharacterToFavoritesUseCase(
            favoriteCharactersDao,
            showSnackbarMessageUseCase,
            adapter,
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
            every { name } returns "name_1"
        }
        val favorite = mockk<FavoriteCharacter>()
        every { adapter.favorite(character) } returns favorite

        subject(character)
        coVerifySequence {
            favoriteCharactersDao.insert(favorite)
            showSnackbarMessageUseCase("name_1 added to favorites")
        }
    }

    @Test
    fun `verify transformer exception is handled`() {
        val character = mockk<RamCharacter>()

        val error = Exception("oops")
        every { adapter.favorite(character) } throws error

        subject(character)
        verify { testTree.e(error) }
        verify { favoriteCharactersDao wasNot Called }
        verify { showSnackbarMessageUseCase wasNot Called }
    }
}
