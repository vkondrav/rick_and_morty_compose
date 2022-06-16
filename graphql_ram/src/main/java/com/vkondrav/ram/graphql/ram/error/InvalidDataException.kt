package com.vkondrav.ram.graphql.ram.error

data class InvalidDataException(override val message: String) : Throwable(message)
