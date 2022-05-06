package com.vkondrav.playground.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(application = TestApplication::class)
@RunWith(RobolectricTestRunner::class)
open class BaseRobolectricTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    protected fun setContent(composable: @Composable () -> Unit) =
        composeTestRule.setContent(composable)

    protected fun withTestRule(block: ComposeTestRule.() -> SemanticsNodeInteraction) {
        with(composeTestRule, block)
    }
}