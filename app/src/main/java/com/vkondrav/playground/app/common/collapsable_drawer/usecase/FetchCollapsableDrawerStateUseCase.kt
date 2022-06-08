package com.vkondrav.playground.app.common.collapsable_drawer.usecase

import com.vkondrav.playground.app.common.collapsable_drawer.state.CollapsableDrawerState
import kotlinx.coroutines.flow.map

class FetchCollapsableDrawerStateUseCase(
    private val state: CollapsableDrawerState,
) {
    operator fun invoke(id: String) = state.state.map { it.contains(id) }
}
