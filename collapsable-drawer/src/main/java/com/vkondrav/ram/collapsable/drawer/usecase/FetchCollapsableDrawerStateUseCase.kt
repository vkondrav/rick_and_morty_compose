package com.vkondrav.ram.collapsable.drawer.usecase

import com.vkondrav.ram.collapsable.drawer.CollapsableDrawerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FetchCollapsableDrawerStateUseCase {
    operator fun invoke(id: String): Flow<Boolean>
}

internal fun fetchCollapsableDrawerStateUseCase(
    state: CollapsableDrawerState,
) = object : FetchCollapsableDrawerStateUseCase {
    override operator fun invoke(id: String) = state.state.map { it.contains(id) }
}
