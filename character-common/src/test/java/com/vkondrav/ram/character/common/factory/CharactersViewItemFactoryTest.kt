package com.vkondrav.ram.character.common.factory

import com.vkondrav.ram.character.common.composable.CharacterViewItem
import com.vkondrav.ram.character.common.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.character.common.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.common.util.asType
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharactersViewItemFactoryTest : BaseTest() {

    private val navigateToCharacterDetailsUseCase =
        mockk<NavigateToCharacterDetailsUseCase>(relaxed = true)
    private val handleCharacterFavoritesUseCase =
        mockk<HandleCharacterFavoritesUseCase>(relaxed = true)

    private lateinit var subject: CharacterViewItemFactory

    @Before
    fun setUp() {
        clearAllMocks()
        subject = CharacterViewItemFactory(
            navigateToCharacterDetailsUseCase,
            handleCharacterFavoritesUseCase,
        )
    }

    @Test
    fun `verify view item adapter`() {
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
