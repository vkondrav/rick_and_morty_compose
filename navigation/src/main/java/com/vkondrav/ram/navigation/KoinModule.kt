package com.vkondrav.ram.navigation

import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.navigation.usecase.FetchAppBarStateUseCase
import com.vkondrav.ram.navigation.usecase.HandleNavigationCommandsUseCase
import com.vkondrav.ram.navigation.usecase.NavigateUpUseCase
import org.koin.dsl.module

val navigationModule = module {
    factory {
        FetchAppBarStateUseCase(
            navigator = get(),
        )
    }
    factory {
        NavigateUpUseCase(
            navigator = get(),
        )
    }
    factory {
        HandleNavigationCommandsUseCase(
            navigator = get(),
        )
    }
    single {
        Navigator(
            flowWrapper = FlowWrapper(),
        )
    }
}
