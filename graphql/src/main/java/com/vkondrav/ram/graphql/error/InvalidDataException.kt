package com.vkondrav.ram.graphql.error

data class InvalidDataException(override val message: String) : Throwable(message)
