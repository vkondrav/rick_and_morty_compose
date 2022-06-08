package com.vkondrav.playground.app.common.collapsable_drawer.usecase

import com.vkondrav.playground.app.common.collapsable_drawer.state.CollapsableDrawerState

class CloseCollapsableDrawerUseCase(
    private val state: CollapsableDrawerState,
) {
    operator fun invoke(id: String) {
        state.close(id)
    }
}
