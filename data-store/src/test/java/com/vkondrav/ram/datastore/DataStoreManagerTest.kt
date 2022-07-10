package com.vkondrav.ram.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import app.cash.turbine.test
import com.vkondrav.ram.test.BaseRobolectricTest
import com.vkondrav.ram.common.util.FlowWrapper
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
    fun `verify setting initial value can only be done once`() = runTest {

        val key = booleanPreferencesKey("key")

        subject.data(key).test {
            subject.initial(key, true)
            awaitItem() shouldBe true
            repeat(10) {
                subject.initial(key, false)
            }
            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify toggle without setting an initial value has no effect`() = runTest {
        val key = booleanPreferencesKey("key")

        subject.data(key).test {
            subject.toggle(key)

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify toggle changes the value`() = runTest {
        val key = booleanPreferencesKey("key")

        subject.data(key).test {
            subject.initial(key, true)
            awaitItem() shouldBe true

            subject.toggle(key)
            awaitItem() shouldBe false

            subject.toggle(key)
            awaitItem() shouldBe true

            subject.toggle(key)
            awaitItem() shouldBe false

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }

    @Test
    fun `verify IOException is consumed by flow`() = runTest {
        subject = DataStoreManager(
            context,
            "data-store",
            FlowTestWrapper(IOException("oh man")),
        )

        val key = booleanPreferencesKey("key")

        subject.data(key).test {
            awaitComplete()
        }
    }

    @Test
    fun `verify other exceptions are thrown by flow`() = runTest {
        val error = TypeCastException("oh man")

        subject = DataStoreManager(
            context,
            "data-store",
            FlowTestWrapper(error),
        )
        val key = booleanPreferencesKey("key")

        subject.data(key).test {
            awaitError() shouldBe error
        }
    }

    private class FlowTestWrapper(private val exception: Exception) : FlowWrapper() {
        override operator fun <T> invoke(flow: Flow<T>) = flow<T> {
            throw exception
        }
    }
}
