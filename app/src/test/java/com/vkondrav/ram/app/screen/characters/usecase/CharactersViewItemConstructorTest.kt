package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.test.BaseTest
import com.vkondrav.ram.util.asType
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharactersViewItemConstructorTest : BaseTest() {

    private val navigateToCharacterDetailsUseCase =
        mockk<NavigateToCharacterDetailsUseCase>(relaxed = true)
    private val handleCharacterFavoritesUseCase =
        mockk<HandleCharacterFavoritesUseCase>(relaxed = true)

    private lateinit var subject: CharactersViewItemConstructor

    @Before
    fun setUp() {
        clearAllMocks()
        subject = CharactersViewItemConstructor(
            navigateToCharacterDetailsUseCase,
            handleCharacterFavoritesUseCase,
        )
    }

    @Test
    fun `verify view item constructor`() {
        val ramCharacter = mockk<RamCharacter> {
            every { id } returns "id_1"
            every { name } returns "name_1"
        }

        subject(ramCharacter).asType<CharacterViewItem>()!!.run {
            character shouldBe ramCharacter

            onClickAction()
            verify { navigateToCharacterDetailsUseCase("id_1", "name_1") }
            verify { handleCharacterFavoritesUseCase wasNot Called }

            onFavoriteAction(true)
            verify { handleCharacterFavoritesUseCase(character, true) }

            onFavoriteAction(false)
            verify { handleCharacterFavoritesUseCase(character, false) }
        }
    }
}
