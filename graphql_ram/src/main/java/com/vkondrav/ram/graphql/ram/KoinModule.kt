package com.vkondrav.ram.graphql.ram

import com.vkondrav.ram.apollo.SERVER_URL
import com.vkondrav.ram.apollo.apolloModule
import org.koin.dsl.module

val ramModule = module {
    single(SERVER_URL) { "https://rickandmortyapi.com/graphql" }
    single<RamRepository> {
        RamRepositoryImp(
            service = get(),
        )
    }
}

val ramModules = listOf(
    ramModule,
    apolloModule,
)
