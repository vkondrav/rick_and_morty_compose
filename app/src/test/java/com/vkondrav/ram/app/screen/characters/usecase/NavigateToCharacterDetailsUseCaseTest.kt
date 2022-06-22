package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigateToCharacterDetailsUseCaseTest: BaseTest() {

    private val navigator = mockk<Navigator>(relaxed = true)
    private lateinit var subject: NavigateToCharacterDetailsUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = NavigateToCharacterDetailsUseCase(navigator)
    }

    @Test
    fun `verify use case`() {
        subject("id_1", "title_1")
        verify { navigator.navigate("character_details/id_1?title=title_1") }
    }
}
