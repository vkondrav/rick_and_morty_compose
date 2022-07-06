package com.vkondrav.ram.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import app.cash.turbine.test
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.navigation.data.AppBarState
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
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

    @Test
    fun `verify back stack observers state and maps to app bar state`() = runTest {
        mockkStatic("com.vkondrav.ram.navigation.Utils")

        val flow = MutableStateFlow<NavBackStackEntry?>(null)

        every { navController.currentBackStackEntryFlow } returns flow.filterNotNull()

        subject.observeBackStack(navController).test {
            every { navController.backQueue } returns mockk {
                every { size } returns 1
            }
            flow.emit(
                mockk {
                    every { arguments } returns mockk {
                        every { title } returns TextResource.Literal("Title_1")
                    }
                },
            )

            awaitItem() shouldBe AppBarState(
                showBackButton = false,
                title = TextResource.Literal("Title_1"),
            )

            every { navController.backQueue } returns mockk {
                every { size } returns 3
            }
            flow.emit(
                mockk {
                    every { arguments } returns mockk {
                        every { title } returns TextResource.Literal("Title_2")
                    }
                },
            )

            awaitItem() shouldBe AppBarState(
                showBackButton = true,
                title = TextResource.Literal("Title_2"),
            )

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    private object FlowTestWrapper : FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow.take(1)
    }
}
