package com.vkondrav.ram.app.common.collapsable_drawer.di

import com.vkondrav.ram.app.common.collapsable_drawer.state.CollapsableDrawerState
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.CloseCollapsableDrawerUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.app.common.collapsable_drawer.usecase.OpenCollapsableDrawerUseCase
import org.koin.dsl.module

val collapsableDrawerModule = module {
    single {
        CollapsableDrawerState()
    }
    factory {
        FetchCollapsableDrawerStateUseCase(
            state = get(),
        )
    }
    factory {
        OpenCollapsableDrawerUseCase(
            state = get(),
        )
    }
    factory {
        CloseCollapsableDrawerUseCase(
            state = get(),
        )
    }
    factory {
        HandleCollapsableDrawerUseCase(
            openCollapsableDrawerUseCase = get(),
            closeCollapsableDrawerUseCase = get(),
        )
    }
}
