package com.vkondrav.ram.location.favorite.di

import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.location.common.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.ram.location.common.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.ram.room.FavoriteLocationsDao
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
                module { //components needed from other modules
                    factory {
                        mockk<FavoriteLocationsDao>()
                    }
                    factory {
                        mockk<RamLocation.Factory>()
                    }
                    factory {
                        mockk<NavigateToLocationDetailsUseCase>()
                    }
                    factory {
                        mockk<HandleLocationFavoriteUseCase>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                favoriteLocationsModule,
            )
            checkModules()
        }
    }
}
