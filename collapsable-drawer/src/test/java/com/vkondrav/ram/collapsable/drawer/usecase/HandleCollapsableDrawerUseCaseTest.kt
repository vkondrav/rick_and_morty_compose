package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class HandleCollapsableDrawerUseCaseTest : BaseTest() {

    private val openCollapsableDrawerUseCase = mockk<OpenCollapsableDrawerUseCase>(relaxed = true)
    private val closeCollapsableDrawerUseCase = mockk<CloseCollapsableDrawerUseCase>(relaxed = true)
    private lateinit var subject: HandleCollapsableDrawerUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = HandleCollapsableDrawerUseCase(
            openCollapsableDrawerUseCase,
            closeCollapsableDrawerUseCase,
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            openCollapsableDrawerUseCase,
            closeCollapsableDrawerUseCase,
        )
    }

    @Test
    fun `verify open drawer case`() {
        subject("id_1", true)
        verify(exactly = 1) { openCollapsableDrawerUseCase("id_1") }
    }

    @Test
    fun `verify close drawer case`() {
        subject("id_1", false)
        verify(exactly = 1) { closeCollapsableDrawerUseCase("id_1") }
    }
}
