package com.vkondrav.ram.character.favorite.di

import com.vkondrav.ram.character.common.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.character.common.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.room.FavoriteCharactersDao
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
                        mockk<FavoriteCharactersDao>()
                    }
                    factory {
                        mockk<RamCharacter.Factory>()
                    }
                    factory {
                        mockk<NavigateToCharacterDetailsUseCase>()
                    }
                    factory {
                        mockk<HandleCharacterFavoritesUseCase>()
                    }
                    factory {
                        Dispatchers.Unconfined
                    }
                },
                favoriteCharactersModule,
            )
            checkModules()
        }
    }
}
