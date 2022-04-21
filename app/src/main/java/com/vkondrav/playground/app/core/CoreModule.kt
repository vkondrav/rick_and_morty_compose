package com.vkondrav.playground.app.core

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModule = module {
    factory {
        Dispatchers.Default
    }
}
