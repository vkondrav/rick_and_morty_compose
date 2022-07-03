package com.vkondrav.ram.datastore

import app.cash.turbine.test
import com.vkondrav.ram.test.BaseRobolectricTest
import com.vkondrav.ram.util.FlowWrapper
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException


class DataStoreManagerTest : BaseRobolectricTest() {

    private lateinit var subject: DataStoreManager

    @Before
    fun setUp() {
        subject = DataStoreManager(context, "data-store", FlowWrapper())
    }

    @Test
    fun `verify setting initial dark theme can only be done once`() = runTest {
        subject.isDarkTheme().test {
            subject.setInitialDarkTheme(true)
            awaitItem() shouldBe true
            repeat(10) {
                subject.setInitialDarkTheme(false)
            }
            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify toggle dark theme without setting an initial value has no effect`() = runTest {
        subject.isDarkTheme().test {
            subject.toggleDarkTheme()

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify toggle dark theme changes the theme`() = runTest {
        subject.isDarkTheme().test {
            subject.setInitialDarkTheme(true)
            awaitItem() shouldBe true

            subject.toggleDarkTheme()
            awaitItem() shouldBe false

            subject.toggleDarkTheme()
            awaitItem() shouldBe true

            subject.toggleDarkTheme()
            awaitItem() shouldBe false

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify IOException is consumed by isDarkTheme`() = runTest {
        subject = DataStoreManager(
            context,
            "data-store",
            FlowTestWrapper(IOException("oh man")),
        )

        subject.isDarkTheme().test {
            awaitComplete()
        }
    }

    @Test
    fun `verify other exceptions are thrown by isDarkTheme`() = runTest {
        val error = TypeCastException("oh man")

        subject = DataStoreManager(
            context,
            "data-store",
            FlowTestWrapper(error),
        )

        subject.isDarkTheme().test {
            awaitError() shouldBe error
        }
    }

    private class FlowTestWrapper(private val exception: Exception) : FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow<T> {
            throw exception
        }
    }
}
