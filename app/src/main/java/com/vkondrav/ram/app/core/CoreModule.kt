package com.vkondrav.ram.app.core

import android.content.Context
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    factory<Context> {
        androidApplication()
    }
    factory {
        Dispatchers.Default
    }
}
