package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.data.CollapsableDrawerState

class OpenCollapsableDrawerUseCase(
    private val state: CollapsableDrawerState,
) {
    operator fun invoke(id: String) {
        state.open(id)
    }
}
