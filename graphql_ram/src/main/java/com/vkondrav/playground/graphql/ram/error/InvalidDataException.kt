package com.vkondrav.playground.graphql.ram.error

data class InvalidDataException(override val message: String) : Throwable(message)
