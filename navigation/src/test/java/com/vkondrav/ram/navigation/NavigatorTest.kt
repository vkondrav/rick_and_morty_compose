package com.vkondrav.ram.navigation

import androidx.navigation.NavController
import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.test.BaseTest
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NavigatorTest : BaseTest() {

    private val navController: NavController = mockk(relaxed = true)
    private lateinit var subject: Navigator

    @Before
    fun setUp() = runTest {
        clearAllMocks()
        subject = Navigator(FlowTestWrapper)
    }

    @Test
    fun `verify navigate up command`() = runTest {
        val navCommands = async {
            subject.handleNavigationCommands(navController)
        }
        val command = async {
            delay(1)
            subject.navigateUp()
        }
        navCommands.await()
        command.await()

        coVerify(exactly = 1) { navController.navigateUp() }
    }

    @Test
    fun `verify navigate route command`() = runTest {
        val navCommands = async {
            subject.handleNavigationCommands(navController)
        }
        val command = async {
            delay(1)
            subject.navigate("route_1")
        }
        navCommands.await()
        command.await()

        coVerify(exactly = 1) { navController.navigate("route_1") }
    }

    private object FlowTestWrapper: FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow.take(1)
    }
}
