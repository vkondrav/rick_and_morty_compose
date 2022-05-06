package com.vkondrav.playground.app.common.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.vkondrav.playground.app.BaseRobolectricTest
import org.junit.Test
import org.mockito.kotlin.mock

class MessageViewItemTest : BaseRobolectricTest() {

    private lateinit var subject: MessageViewItem

    @Test
    fun `verify attribute are bound to composable`() {
        subject = MessageViewItem("Test 1", Icons.Default.AccountCircle)
        setContent {
            subject.Composable(action = mock())
        }

        withTestRule {
            onNodeWithTag("message", useUnmergedTree = true)
                .assertExists()
                .assertTextEquals("Test 1")

            onNodeWithTag("icon", useUnmergedTree = true)
                .assertExists()
        }
    }
}