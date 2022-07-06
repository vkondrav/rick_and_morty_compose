package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.CollapsableDrawerState

interface OpenCollapsableDrawerUseCase {
    operator fun invoke(id: String)
}

internal fun openCollapsableDrawerUseCase(
    state: CollapsableDrawerState,
) = object : OpenCollapsableDrawerUseCase {
    override operator fun invoke(id: String) {
        state.open(id)
    }
}
