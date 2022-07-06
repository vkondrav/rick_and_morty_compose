package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.CollapsableDrawerState
import com.vkondrav.ram.test.BaseTest
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class OpenCollapsableDrawerUseCaseTest: BaseTest() {

    @Test
    fun `verify use case`() {
        val state = mockk<CollapsableDrawerState>(relaxed = true)
        openCollapsableDrawerUseCase(state)("id")
        verify(exactly = 1) { state.open("id") }
        confirmVerified(state)
    }
}
