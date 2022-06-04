package com.vkondrav.playground.graphql.ram

import com.vkondrav.apollo.SERVER_URL
import com.vkondrav.apollo.apolloModule
import com.vkondrav.playground.graphql.ram.domain.RamCharacterTransformer
import org.koin.dsl.module

val ramModule = module {
    factory {
        RamCharacterTransformer
    }
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
