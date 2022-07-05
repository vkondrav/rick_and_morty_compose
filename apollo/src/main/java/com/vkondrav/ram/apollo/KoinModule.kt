package com.vkondrav.ram.apollo

import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.vkondrav.ram.common.util.TargetWrapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apolloModule = module {
    factory<NormalizedCacheFactory> {
        MemoryCacheFactory(maxSizeBytes = CACHE_SIZE_BYTES)
    }
    factory {
        Client(
            serverUrl = get(SERVER_URL),
            cache = get(),
            wrapper = TargetWrapper(),
        )
    }
    single {
        Service(
            client = get<Client>().build(),
        )
    }
}

val SERVER_URL = named("SERVER_URL")
private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024 //10MB
