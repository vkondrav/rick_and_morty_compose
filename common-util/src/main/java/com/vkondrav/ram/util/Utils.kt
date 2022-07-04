@file:JvmName("Utils")

package com.vkondrav.ram.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<String>>.mapToSet() = map { it.toSet() }

@Suppress("UNCHECKED_CAST")
fun <T> Any.asType(): T? = this as? T
