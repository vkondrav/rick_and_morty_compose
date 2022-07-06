package com.vkondrav.ram.navigation.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.navigation.data.AppBarState
import com.vkondrav.ram.navigation.Navigator
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Test

class FetchAppBarStateUseCaseTest: BaseTest() {

    private val navigator: Navigator = mockk(relaxed = true)
    private lateinit var subject: FetchAppBarStateUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = fetchAppBarStateUseCase(navigator)
    }

    @Test
    fun `verify use case`() {
        val navController = mockk<NavController>()
        val flow = emptyFlow<AppBarState>()
        every { navigator.observeBackStack(navController) } returns flow
        subject(navController) shouldBe flow
    }
}
