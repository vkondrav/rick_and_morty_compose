package com.vkondrav.playground.app.common.nav

import android.os.Bundle

internal const val NAV_ID = "id"
internal const val NAV_TITLE = "title"

internal val Bundle.id get() = this.getString(NAV_ID)
internal val Bundle.title get() = this.getString(NAV_TITLE)
