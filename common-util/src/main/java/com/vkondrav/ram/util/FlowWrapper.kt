package com.vkondrav.ram.util

import kotlinx.coroutines.flow.Flow

/**
 * this class wraps flows and makes them finite in
 * unit test to allow them to complete
 */
open class FlowWrapper {
    open operator fun <T> invoke(flow: Flow<T>) = flow
}
