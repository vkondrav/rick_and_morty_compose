package com.vkondrav.ram.collapsable.drawer

import com.vkondrav.ram.collapsable.drawer.usecase.HandleCollapsableDrawerUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.closeCollapsableDrawerUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.fetchCollapsableDrawerStateUseCase
import com.vkondrav.ram.collapsable.drawer.usecase.openCollapsableDrawerUseCase
import org.koin.dsl.module

val collapsableDrawerModule = module {
    single {
        CollapsableDrawerState()
    }
    factory {
        fetchCollapsableDrawerStateUseCase(
            state = get(),
        )
    }
    factory {
        openCollapsableDrawerUseCase(
            state = get(),
        )
    }
    factory {
        closeCollapsableDrawerUseCase(
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
