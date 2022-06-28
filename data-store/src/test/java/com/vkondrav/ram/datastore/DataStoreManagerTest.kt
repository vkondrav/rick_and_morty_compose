package com.vkondrav.ram.datastore

import app.cash.turbine.test
import com.vkondrav.ram.test.BaseRobolectricTest
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class DataStoreManagerTest : BaseRobolectricTest() {

    private lateinit var subject: DataStoreManager

    @Before
    fun setUp() {
        subject = DataStoreManager(context, "data-store")
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
    fun `verify toggle dark theme changes the theme`() = runTest {
        subject.isDarkTheme().test {
            subject.setInitialDarkTheme(true)
            awaitItem() shouldBe true

            subject.toggleDarkTheme()
            awaitItem() shouldBe false

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }
}
