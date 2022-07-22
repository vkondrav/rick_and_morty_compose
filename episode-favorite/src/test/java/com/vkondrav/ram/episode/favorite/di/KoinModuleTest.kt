package com.vkondrav.ram.episode.favorite.di

import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.episode.common.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.episode.common.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.room.FavoriteEpisodesDao
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
                        mockk<FavoriteEpisodesDao>()
                    }
                    factory {
                        mockk<RamEpisode.Factory>()
                    }
                    factory {
                        mockk<NavigateToEpisodeDetailsUseCase>()
                    }
                    factory {
                        mockk<HandleEpisodeFavoriteUseCase>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                favoriteEpisodesModule,
            )
            checkModules()
        }
    }
}
