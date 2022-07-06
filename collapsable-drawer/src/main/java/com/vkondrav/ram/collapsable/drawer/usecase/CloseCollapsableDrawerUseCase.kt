package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.CollapsableDrawerState

interface CloseCollapsableDrawerUseCase{
    operator fun invoke(id: String)
}

internal fun closeCollapsableDrawerUseCase(
    state: CollapsableDrawerState,
) = object : CloseCollapsableDrawerUseCase {
    override operator fun invoke(id: String) {
        state.close(id)
    }
}
