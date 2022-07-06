package com.vkondrav.ram.navigation

import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.navigation.usecase.fetchAppBarStateUseCase
import com.vkondrav.ram.navigation.usecase.handleNavigationCommandsUseCase
import com.vkondrav.ram.navigation.usecase.navigateToRouteUseCase
import com.vkondrav.ram.navigation.usecase.navigateUpUseCase
import org.koin.dsl.module

val navigationModule = module {
    factory {
        fetchAppBarStateUseCase(
            navigator = get(),
        )
    }
    factory {
        navigateToRouteUseCase(
            navigator = get(),
        )
    }
    factory {
        navigateUpUseCase(
            navigator = get(),
        )
    }
    factory {
        handleNavigationCommandsUseCase(
            navigator = get(),
        )
    }
    single {
        Navigator(
            flowWrapper = FlowWrapper(),
        )
    }
}
