package com.vkondrav.ram.snackbar

import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SnackbarControllerTest : BaseTest() {

    private val snackbarData: SnackbarData = mockk(relaxed = true)
    private val snackbarHostState: SnackbarHostState = mockk(relaxed = true)

    private lateinit var subject: SnackbarController

    @Before
    fun setUp() {
        clearAllMocks()
        every { snackbarHostState.currentSnackbarData } returns snackbarData
        subject = SnackbarController(FlowTestWrapper)
    }

    @Test
    fun `verify message command`() = runTest {
        val messageCommands = async {
            subject.handleSnackbarState(snackbarHostState)
        }
        val command = async {
            delay(1)
            subject.showMessage("hello world")
        }
        messageCommands.await()
        command.await()

        coVerifySequence {
            snackbarHostState.currentSnackbarData
            snackbarData.dismiss()
            snackbarHostState.showSnackbar("hello world")
        }
    }

    private object FlowTestWrapper : FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow.take(1)
    }
}
