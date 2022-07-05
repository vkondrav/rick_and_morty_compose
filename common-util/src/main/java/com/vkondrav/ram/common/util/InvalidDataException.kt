package com.vkondrav.ram.common.util

data class InvalidDataException(override val message: String) : Throwable(message)
