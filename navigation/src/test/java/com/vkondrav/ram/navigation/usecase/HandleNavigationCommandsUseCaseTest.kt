package com.vkondrav.ram.navigation.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.navigation.Navigator
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HandleNavigationCommandsUseCaseTest {

    private val navigator = mockk<Navigator>(relaxed = true)
    private lateinit var subject: HandleNavigationCommandsUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = handleNavigationCommandsUseCase(navigator)
    }

    @Test
    fun `verify use case`() = runTest {
        val navController = mockk<NavController>()
        subject(navController)
        coVerify(exactly = 1) { navigator.handleNavigationCommands(navController) }
    }
}
