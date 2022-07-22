package com.vkondrav.ram.location.all.di

import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.navigation.usecase.NavigateToRouteUseCase
import com.vkondrav.ram.room.FavoriteLocationsDao
import com.vkondrav.ram.snackbar.usecase.ShowSnackbarMessageUseCase
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
                module { // components needed from other modules
                    factory {
                        mockk<NavigateToRouteUseCase>()
                    }
                    factory {
                        mockk<FavoriteLocationsDao>()
                    }
                    factory {
                        mockk<ShowSnackbarMessageUseCase>()
                    }
                    factory {
                        mockk<RamLocation.Adapter>()
                    }
                    factory {
                        mockk<RamRepository>()
                    }
                    factory {
                        mockk<RamPage.Factory>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                locationsModule,
            )
            checkModules()
        }
    }
}
