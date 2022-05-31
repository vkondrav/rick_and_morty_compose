package com.vkondrav.playground.graphql.ram.domain

import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.playground.graphql.ram.error.InvalidDataException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.Test

class RamCharacterTest {

    private lateinit var subject: RamCharacter

    @Test
    fun `verify happy path transformation from character fragment`() {
        subject = RamCharacter(
            characterFragment.copy(
                id = "id_1",
                name = "name_1",
                status = "status_1",
                species = "species_1",
                image = "image_1",
            ),
        )

        with(subject) {
            id shouldBe "id_1"
            name shouldBe "name_1"
            status shouldBe "status_1"
            species shouldBe "species_1"
            image shouldBe "image_1"
        }
    }

    @Test
    fun `verify id null throws error`() {
        shouldThrow<InvalidDataException> {
            RamCharacter(
                characterFragment.copy(
                    id = null,
                ),
            )
        }.message shouldBe "missing id"
    }

    @Test
    fun `verify name null throws error`() {
        shouldThrow<InvalidDataException> {
            RamCharacter(
                characterFragment.copy(
                    name = null,
                ),
            )
        }.message shouldBe "missing name"
    }

    private val characterFragment = CharacterFragment(
        id = "id_1",
        name = "name_1",
        status = "status_1",
        species = "species_1",
        image = "image_1",
    )
}
