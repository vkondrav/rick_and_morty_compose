package com.vkondrav.ram.datastore

import com.vkondrav.ram.test.BaseRobolectricTest
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : BaseRobolectricTest(), KoinTest {

    @Test
    fun `verify koin module`() {
        koinApplication {
            androidContext(context)
            modules(
                dataStoreModule,
                module {
                    factory(DATASTORE_NAME) { "datastore" }
                },
            )
            checkModules()
        }
    }
}
