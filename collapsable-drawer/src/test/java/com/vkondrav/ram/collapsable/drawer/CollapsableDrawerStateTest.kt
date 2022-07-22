package com.vkondrav.ram.collapsable.drawer

import app.cash.turbine.test
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CollapsableDrawerStateTest : BaseTest() {

    private lateinit var subject: CollapsableDrawerState

    @Before
    fun setUp() {
        subject = CollapsableDrawerState()
    }

    @Test
    fun `verify open and close drawer method`() = runTest {
        subject.state.test {
            subject.open("id_1")
            awaitItem() shouldBe setOf("id_1")
            subject.open("id_2")
            awaitItem() shouldBe setOf("id_1", "id_2")
            subject.open("id_3")
            awaitItem() shouldBe setOf("id_1", "id_2", "id_3")

            subject.close("id_1")
            awaitItem() shouldBe setOf("id_2", "id_3")
            subject.close("id_2")
            awaitItem() shouldBe setOf("id_3")
            subject.close("id_3")
            awaitItem() shouldBe emptySet()

            subject.close("garbage")
            awaitItem() shouldBe emptySet()

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }
}
