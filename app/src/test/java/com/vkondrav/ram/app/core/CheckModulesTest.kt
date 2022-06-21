package com.vkondrav.ram.app.core

import com.vkondrav.ram.test.BaseRobolectricTest
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CheckModulesTest : BaseRobolectricTest(), KoinTest {

    @Test
    fun `verify all koin modules`() {
        koinApplication {
            androidContext(context)
            appModules()
            checkModules()
        }
    }
}
