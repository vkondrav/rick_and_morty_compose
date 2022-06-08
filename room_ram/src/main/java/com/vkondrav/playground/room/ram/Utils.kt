package com.vkondrav.playground.room.ram

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<String>>.mapToSet() = map { it.toSet() }
