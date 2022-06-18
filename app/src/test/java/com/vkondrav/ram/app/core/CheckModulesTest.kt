package com.vkondrav.ram.app.core

import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.navigation.NavController
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CheckModulesTest : KoinTest {

    @Test
    fun `verify all koin modules`() {
        koinApplication {
            androidContext(RuntimeEnvironment.getApplication())
            appModules().modules(
                module { //these components are loaded at runtime
                    factory<NavController> { mock() }
                    factory<SnackbarHostState> { mock() }
                    factory<DrawerState> { mock() }
                },
            )
            checkModules()
        }
    }
}
