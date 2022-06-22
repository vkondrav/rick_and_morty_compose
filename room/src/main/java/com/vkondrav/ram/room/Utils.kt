@file:JvmName("Utils")

package com.vkondrav.ram.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<String>>.mapToSet() = map { it.toSet() }
