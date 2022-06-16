package com.vkondrav.ram.app.common.navigation

import android.os.Bundle
import com.vkondrav.ram.app.common.utils.TextResource

internal const val NAV_ID = "id"
internal const val NAV_TITLE = "title"

internal val Bundle.id: String? get() = getString(NAV_ID)
internal val Bundle.title: TextResource? get() = getParcelable(NAV_TITLE)
