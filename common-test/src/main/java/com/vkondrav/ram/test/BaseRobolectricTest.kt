package com.vkondrav.ram.test

import android.content.Context
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@Config(application = TestApplication::class)
@RunWith(RobolectricTestRunner::class)
abstract class BaseRobolectricTest : BaseTest() {

    protected val context: Context by lazy { RuntimeEnvironment.getApplication() }
}
