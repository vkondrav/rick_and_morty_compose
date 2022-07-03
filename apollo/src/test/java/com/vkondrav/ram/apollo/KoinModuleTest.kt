package com.vkondrav.ram.apollo

import com.vkondrav.ram.test.BaseTest
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : BaseTest(), KoinTest {

    @Test
    fun `verify koin module`() {
        koinApplication {
            modules(
                apolloModule,
                module {
                    factory(SERVER_URL) { "test_server" }
                },
            )
            checkModules()
        }
    }
}
