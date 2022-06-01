package com.vkondrav.apollo

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.api.Error
import com.apollographql.apollo3.api.Operation

class ApolloErrorsException(
    message: String? = null,
    cause: Throwable? = null,
    errors: List<Error>? = null,
) : ApolloException(message, cause)

val <D : Operation.Data> ApolloResponse<D>.dataOrThrow: D
    get() = runCatching {
        dataAssertNoErrors
    }.getOrElse { e ->
        throw ApolloErrorsException(e.message, e.cause, errors)
    }
