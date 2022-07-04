package com.vkondrav.ram.app.common.navigation

import android.os.Bundle
import com.vkondrav.ram.util.TextResource

internal const val NAV_ID = "id"
internal const val NAV_TITLE = "title"

internal val Bundle.id: String? get() = getString(NAV_ID)
internal val Bundle?.title: TextResource
    get() = this?.getString(NAV_TITLE)?.let {
        TextResource.Literal(it)
    } ?: this?.getParcelable(NAV_TITLE)
    ?: TextResource.Literal("")

