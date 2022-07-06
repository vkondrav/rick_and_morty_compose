package com.vkondrav.ram.collapsable.drawer

import com.vkondrav.ram.collapsable.drawer.data.CollapsableDrawerState
import com.vkondrav.ram.collapsable.drawer.usecase.CloseCollapsableDrawerUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.FetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.OpenCollapsableDrawerUseCase
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
