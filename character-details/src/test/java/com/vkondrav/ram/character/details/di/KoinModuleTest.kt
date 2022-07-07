package com.vkondrav.ram.character.details.di

import com.vkondrav.ram.collapsable.drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.domain.RamCharacterDetails
import com.vkondrav.ram.episode.all.factory.EpisodeViewItemFactory
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.location.all.factory.LocationViewItemFactory
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.room.FavoriteEpisodesDao
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
                        mockk<RamRepository>()
                    }
                    factory {
                        mockk<FavoriteCharactersDao>()
                    }
                    factory {
                        mockk<FavoriteLocationsDao>()
                    }
                    factory {
                        mockk<FavoriteEpisodesDao>()
                    }
                    factory {
                        mockk<RamCharacterDetails.Factory>()
                    }
                    factory {
                        mockk<FetchCollapsableDrawerStateUseCase>()
                    }
                    factory {
                        mockk<HandleCollapsableDrawerUseCase>()
                    }
                    factory {
                        mockk<LocationViewItemFactory>()
                    }
                    factory {
                        mockk<EpisodeViewItemFactory>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                characterDetailsModule,
            )
            checkModules()
        }
    }
}
