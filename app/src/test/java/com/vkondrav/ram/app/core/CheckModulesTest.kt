package com.vkondrav.ram.app.core

import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CheckModulesTest : KoinTest {

    @Test
    fun `verify all koin modules`() {
        koinApplication {
            androidContext(RuntimeEnvironment.getApplication())
            appModules()
            checkModules()
        }
    }
}
