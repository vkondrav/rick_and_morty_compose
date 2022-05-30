package com.vkondrav.apollo

import org.koin.core.qualifier.named
import org.koin.dsl.module

val apolloModule = module {
    factory<Client> {
        ClientImpl(
            serverUrl = get(SERVER_URL),
        )
    }
    single<Service> {
        ServiceImpl(
            client = get<Client>().build(),
        )
    }
}

val SERVER_URL = named("SERVER_URL")
