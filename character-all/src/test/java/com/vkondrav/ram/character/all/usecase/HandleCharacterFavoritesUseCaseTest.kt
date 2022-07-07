package com.vkondrav.ram.character.all.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.test.BaseTest
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class HandleCharacterFavoritesUseCaseTest : BaseTest() {

    private val addCharacterToFavoritesUseCase =
        mockk<AddCharacterToFavoritesUseCase>(relaxed = true)
    private val removeCharacterFromFavoritesUseCase =
        mockk<RemoveCharacterFromFavoritesUseCase>(relaxed = true)

    private lateinit var subject: HandleCharacterFavoritesUseCase

    @Before
    fun setUp() {
        subject = HandleCharacterFavoritesUseCase(
            addCharacterToFavoritesUseCase,
            removeCharacterFromFavoritesUseCase,
        )
    }

    @Test
    fun `verify adding character to favorites`() {
        val character = mockk<RamCharacter>()
        subject(character, true)
        verify(exactly = 1) { addCharacterToFavoritesUseCase(character) }
        verify(exactly = 0) { removeCharacterFromFavoritesUseCase(character) }
    }

    @Test
    fun `verify removing character from favorites`() {
        val character = mockk<RamCharacter>()
        subject(character, false)
        verify(exactly = 0) { addCharacterToFavoritesUseCase(character) }
        verify(exactly = 1) { removeCharacterFromFavoritesUseCase(character) }
    }
}
