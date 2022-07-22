package com.vkondrav.ram.navigation.usecase

import com.vkondrav.ram.navigation.Navigator
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigateUpUseCaseTest : BaseTest() {

    private val navigator: Navigator = mockk(relaxed = true)
    private lateinit var subject: NavigateUpUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = navigateUpUseCase(navigator)
    }

    @Test
    fun `verify use case`() {
        subject()
        verify(exactly = 1) { navigator.navigateUp() }
    }
}
