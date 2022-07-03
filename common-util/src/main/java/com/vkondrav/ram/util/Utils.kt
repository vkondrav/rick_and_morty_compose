@file:JvmName("Utils")

package com.vkondrav.ram.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<String>>.mapToSet() = map { it.toSet() }

/**
 * use this class to wrap inaccessible targets that you cannot
 * verify attributes off, kind of like shadows from Robolectric
 */
open class TargetWrapper {
    open operator fun <T> invoke(target: T): T = target
}

/**
 * this class wraps flows and makes them finite in
 * unit test to allow them to complete
 */
open class FlowWrapper {
    open operator fun <T> invoke(flow: Flow<T>) = flow
}

@Suppress("UNCHECKED_CAST")
fun <T> Any.asType(): T? = this as? T
