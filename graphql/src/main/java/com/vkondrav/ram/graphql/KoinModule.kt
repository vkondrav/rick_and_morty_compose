package com.vkondrav.ram.graphql

import com.vkondrav.ram.apollo.SERVER_URL
import com.vkondrav.ram.apollo.apolloModule
import org.koin.dsl.module

val ramModule = module {
    single {
        RamRepository(
            service = get(),
        )
    }
}

val ramModules = listOf(
    ramModule,
    apolloModule,
)
