package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.data.CollapsableDrawerState
import kotlinx.coroutines.flow.map

class FetchCollapsableDrawerStateUseCase(
    private val state: CollapsableDrawerState,
) {
    operator fun invoke(id: String) = state.state.map { it.contains(id) }
}
