package com.vkondrav.ram.drawer.di

import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase
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
    fun `verify koin modules`() {
        koinApplication {
            modules(
                module {
                    factory {
                        mockk<NavigateToRouteUseCase>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                drawerModule,
            )
            checkModules()
        }
    }
}
