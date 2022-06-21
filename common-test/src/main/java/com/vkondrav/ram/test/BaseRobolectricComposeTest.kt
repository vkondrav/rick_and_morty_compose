package com.vkondrav.ram.test

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class BaseRobolectricComposeTest : BaseRobolectricTest() {
    @get:Rule
    val composeTestRule = createComposeRule()

    protected fun setContent(composable: @Composable () -> Unit) =
        composeTestRule.setContent(composable)

    protected fun withTestRule(block: ComposeTestRule.() -> SemanticsNodeInteraction) {
        with(composeTestRule, block)
    }
}
