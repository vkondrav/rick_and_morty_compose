package com.vkondrav.ram.collapsable.drawer.usecase

import app.cash.turbine.test
import com.vkondrav.ram.collapsable.drawer.data.CollapsableDrawerState
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchCollapsableDrawerStateUseCaseTest: BaseTest() {

    @Test
    fun `verify use case`() = runTest {
        val state = mockk<CollapsableDrawerState> {
            every { state } returns flowOf(
                setOf("id_1"),
                setOf("id_1", "id_2"),
                setOf("id_1", "id_2", "id_3"),
                emptySet(),
            )
        }

        FetchCollapsableDrawerStateUseCase(state)("id_3").test {
            awaitItem() shouldBe false
            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false

            awaitComplete()
        }
    }
}
