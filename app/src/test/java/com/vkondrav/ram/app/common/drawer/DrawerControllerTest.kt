package com.vkondrav.ram.app.common.drawer

import androidx.compose.material.DrawerState
import com.vkondrav.ram.domain.util.FlowWrapper
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DrawerControllerTest: BaseTest() {

    private val drawerState: DrawerState = mockk(relaxed = true)
    private lateinit var subject: DrawerController

    @Before
    fun setUp() {
        clearAllMocks()
        subject = DrawerController(FlowTestWrapper)
    }

    @Test
    fun `verify open command`() = runTest {
        val drawerCommands = async {
            subject.handleDrawerState(drawerState)
        }
        val command = async {
            delay(1)
            subject.open()
        }
        drawerCommands.await()
        command.await()

        coVerify(exactly = 1) { drawerState.open() }
    }

    @Test
    fun `verify close command`() = runTest {
        val drawerCommands = async {
            subject.handleDrawerState(drawerState)
        }
        val command = async {
            delay(1)
            subject.close()
        }
        drawerCommands.await()
        command.await()

        coVerify(exactly = 1) { drawerState.close() }
    }

    private object FlowTestWrapper: FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow.take(1)
    }
}
