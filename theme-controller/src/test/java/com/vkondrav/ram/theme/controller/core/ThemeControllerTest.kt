package com.vkondrav.ram.theme.controller.core

import androidx.datastore.preferences.core.Preferences
import com.vkondrav.ram.datastore.DataStoreManager
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class ThemeControllerTest : BaseTest() {

    private val dataStoreManager = mockk<DataStoreManager>(relaxed = true)
    private val testTree = mockk<Timber.Tree>(relaxed = true)
    private lateinit var subject: ThemeController

    @Before
    fun setUp() {
        clearAllMocks()
        Timber.plant(testTree)
        subject = ThemeController(dataStoreManager, Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Timber.uproot(testTree)
    }

    @Test
    fun `verify is theme dark flow`() {
        val capturedKey1 = slot<Preferences.Key<Boolean>>()
        val capturedKey2 = slot<Preferences.Key<Boolean>>()

        coEvery { dataStoreManager.initial(capture(capturedKey1), any()) }
        coEvery { dataStoreManager.data(capture(capturedKey2)) } returns emptyFlow()

        subject.isThemeDark(true)

        coVerifySequence {
            dataStoreManager.initial(any(), true)
            capturedKey1.captured.name shouldBe "is_dark_theme"

            dataStoreManager.data(any())
            capturedKey2.captured.name shouldBe "is_dark_theme"
        }
    }

    @Test
    fun `verify toggle dark theme`() {
        val capturedKey = slot<Preferences.Key<Boolean>>()
        coEvery { dataStoreManager.toggle(capture(capturedKey)) }
        subject.toggleTheme()
        coVerify {
            dataStoreManager.toggle(any())
            capturedKey.captured.name shouldBe "is_dark_theme"
        }
    }

    @Test
    fun `verify exception is handled`() {
        val error = Exception("oops")
        coEvery { dataStoreManager.toggle(any()) } throws error

        subject.toggleTheme()
        verify { testTree.e(error) }
    }
}