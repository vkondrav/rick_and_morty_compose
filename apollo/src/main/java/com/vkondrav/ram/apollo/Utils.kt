package com.vkondrav.ram.apollo

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.exception.ApolloException

val <D : Operation.Data> ApolloResponse<D>.dataOrThrow: D
    get() = runCatching {
        dataAssertNoErrors
    }.getOrElse { e ->
        throw ApolloException(e.message, e.cause)
    }
