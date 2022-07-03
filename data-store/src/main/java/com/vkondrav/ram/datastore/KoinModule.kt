package com.vkondrav.ram.datastore

import com.vkondrav.ram.util.FlowWrapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataStoreModule = module {
    single {
        DataStoreManager(
            context = get(),
            name = get(DATASTORE_NAME),
            wrapper = FlowWrapper(),
        )
    }
}

val DATASTORE_NAME = named("settings")
