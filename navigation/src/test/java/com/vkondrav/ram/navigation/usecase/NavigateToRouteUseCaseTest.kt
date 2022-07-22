package com.vkondrav.ram.navigation.usecase

import com.vkondrav.ram.navigation.Navigator
import com.vkondrav.ram.test.BaseTest
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class NavigateToRouteUseCaseTest : BaseTest() {

    @Test
    fun `verify use case`() {
        val navigator = mockk<Navigator>(relaxed = true)
        navigateToRouteUseCase(navigator)("route_1")
        verify(exactly = 1) { navigator.navigate("route_1") }
        confirmVerified(navigator)
    }
}
