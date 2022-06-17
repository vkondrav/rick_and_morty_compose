package com.vkondrav.ram.test

import org.junit.Rule
import org.junit.rules.Timeout

abstract class BaseTest {

    @get:Rule
    open var globalTimeout: Timeout = Timeout.seconds(60)
}