package com.vkondrav.ram.character.common.usecase

import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigateToCharacterDetailsUseCaseTest : BaseTest() {

    private val navigateToRouteUseCase = mockk<NavigateToRouteUseCase>(relaxed = true)
    private lateinit var subject: NavigateToCharacterDetailsUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = NavigateToCharacterDetailsUseCase(navigateToRouteUseCase)
    }

    @Test
    fun `verify use case`() {
        subject("id_1", "title_1")
        verify(exactly = 1) { navigateToRouteUseCase("character_details/id_1?title=title_1") }
        confirmVerified(navigateToRouteUseCase)
    }
}
