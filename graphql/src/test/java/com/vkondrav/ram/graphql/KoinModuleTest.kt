package com.vkondrav.ram.graphql

import com.vkondrav.ram.apollo.SERVER_URL
import com.vkondrav.ram.test.BaseTest
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : BaseTest(), KoinTest {

    @Test
    fun `verify koin modules`() {
        koinApplication {
            modules(
                listOf(
                    ramModules,
                    listOf(
                        module {
                            factory(SERVER_URL) { "test_server" }
                        },
                    ),
                ).flatten(),
            )
            checkModules()
        }
    }
}
