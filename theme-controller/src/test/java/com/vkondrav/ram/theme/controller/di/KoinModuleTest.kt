package com.vkondrav.ram.theme.controller.di

import com.vkondrav.ram.datastore.DataStoreManager
import com.vkondrav.ram.test.BaseTest
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
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
                module {
                    factory {
                        mockk<DataStoreManager>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                themeControllerModule,
            )
            checkModules()
        }
    }
}
