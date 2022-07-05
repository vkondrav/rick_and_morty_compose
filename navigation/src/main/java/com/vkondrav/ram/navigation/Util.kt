package com.vkondrav.ram.navigation

import android.os.Bundle
import com.vkondrav.ram.common.ui.TextResource

const val NAV_ID = "id"
const val NAV_TITLE = "title"

val Bundle.id: String? get() = getString(NAV_ID)
val Bundle?.title: TextResource
    get() = this?.getString(NAV_TITLE)?.let {
        TextResource.Literal(it)
    } ?: this?.getParcelable(NAV_TITLE)
    ?: TextResource.Literal("")

