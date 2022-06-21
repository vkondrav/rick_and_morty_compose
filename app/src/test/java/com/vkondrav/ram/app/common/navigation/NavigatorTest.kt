package com.vkondrav.ram.app.common.navigation

import androidx.navigation.NavController
import com.vkondrav.ram.domain.util.FlowWrapper
import com.vkondrav.ram.test.BaseTest
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class NavigatorTest : BaseTest() {

    private lateinit var navController: NavController
    private lateinit var subject: Navigator

    @Before
    fun setUp() = runTest {
        navController = mock()
        subject = Navigator(FlowTestWrapper)
    }

    @Test
    fun `verify navigate up command`() = runTest {
        val navCommands = async {
            subject.handleNavigationCommands(navController)
        }
        val command = async {
            subject.navigateUp()
        }
        navCommands.join()
        command.join()

        verify(navController).navigateUp()
    }

    @Test
    fun `verify navigate route command`() = runTest {
        val navCommands = async {
            subject.handleNavigationCommands(navController)
        }
        val command = async {
            subject.navigate("route_1")
        }
        navCommands.join()
        command.join()

        verify(navController).navigate("route_1")
    }

    private object FlowTestWrapper: FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow.take(1)
    }
}
