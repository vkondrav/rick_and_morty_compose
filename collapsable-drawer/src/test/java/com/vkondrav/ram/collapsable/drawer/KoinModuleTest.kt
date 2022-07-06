package com.vkondrav.ram.collapsable.drawer

import com.vkondrav.ram.test.BaseTest
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : BaseTest(), KoinTest {

    @Test
    fun `verify koin module`() {
        koinApplication {
            modules(collapsableDrawerModule)
            checkModules()
        }
    }
}
