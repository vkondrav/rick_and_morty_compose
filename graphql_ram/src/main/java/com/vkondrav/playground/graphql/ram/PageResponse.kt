package com.vkondrav.playground.graphql.ram

import com.apollographql.apollo3.api.Fragment
import com.vkondrav.graphql.ram.fragment.InfoFragment

data class PageResponse<T: Fragment.Data>(
    val info: InfoFragment,
    val items: List<T>,
)
