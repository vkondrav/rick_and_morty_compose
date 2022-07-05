package com.vkondrav.ram.navigation

import com.vkondrav.ram.test.BaseTest
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest

class KoinModuleTest : BaseTest(), KoinTest {

    @Test
    fun `verify koin module`() {
        koinApplication {
            modules(navigationModule)
        }
    }
}