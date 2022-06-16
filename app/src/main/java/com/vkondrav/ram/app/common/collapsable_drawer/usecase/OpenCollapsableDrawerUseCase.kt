package com.vkondrav.ram.app.common.collapsable_drawer.usecase

import com.vkondrav.ram.app.common.collapsable_drawer.state.CollapsableDrawerState

class OpenCollapsableDrawerUseCase(
    private val state: CollapsableDrawerState,
) {
    operator fun invoke(id: String) {
        state.open(id)
    }
}
