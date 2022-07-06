package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.data.CollapsableDrawerState
import com.vkondrav.ram.test.BaseTest
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class CloseCollapsableDrawerUseCaseTest: BaseTest() {

    @Test
    fun `verify use case`() {
        val state = mockk<CollapsableDrawerState>(relaxed = true)
        CloseCollapsableDrawerUseCase(state)("id")
        verify(exactly = 1) { state.close("id") }
        confirmVerified(state)
    }
}
