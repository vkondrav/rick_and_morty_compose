package com.vkondrav.ram.common.util

/**
 * use this class to wrap inaccessible targets that you cannot
 * verify attributes off, kind of like shadows from Robolectric
 */
open class TargetWrapper {
    open operator fun <T> invoke(target: T): T = target
}
