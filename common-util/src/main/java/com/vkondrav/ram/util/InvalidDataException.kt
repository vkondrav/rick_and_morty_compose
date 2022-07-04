package com.vkondrav.ram.util

data class InvalidDataException(override val message: String) : Throwable(message)
