package com.vkondrav.ram.datastore

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataStoreModule = module {
    single {
        DataStoreManager(
            context = get(),
            name = get(DATASTORE_NAME),
        )
    }
}

val DATASTORE_NAME = named("settings")
