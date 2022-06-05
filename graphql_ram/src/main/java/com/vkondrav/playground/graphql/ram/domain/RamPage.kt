package com.vkondrav.playground.graphql.ram.domain

data class RamPage<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>,
)
