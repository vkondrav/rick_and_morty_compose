package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.domain.RamPage
import com.vkondrav.ram.graphql.PageResponse
import com.vkondrav.ram.graphql.RamRepository
import com.vkondrav.ram.graphql.generated.fragment.CharacterFragment
import com.vkondrav.ram.room.FavoriteCharactersDao
import com.vkondrav.ram.room.mapToSet
import com.vkondrav.ram.test.BaseTest
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FetchCharactersUseCaseTest : BaseTest() {

    private val ramRepository = mockk<RamRepository>()
    private val favoriteCharactersDao = mockk<FavoriteCharactersDao>()
    private val transformer = mockk<RamPage.SourceConstructor>()

    private lateinit var subject: FetchCharactersUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        subject = FetchCharactersUseCase(
            ramRepository,
            favoriteCharactersDao,
            transformer,
        )
    }

    @Test
    fun `verify happy path use case`() = runTest {
        mockkStatic("com.vkondrav.ram.room.Utils")

        val pageResponse = mockk<PageResponse<CharacterFragment>>()
        coEvery { ramRepository.fetchCharacters(0) } returns pageResponse

        val favoriteCharactersList = mockk<Flow<List<String>>>()
        val favoriteCharactersSet = mockk<Flow<Set<String>>>()
        every { favoriteCharactersList.mapToSet() } returns favoriteCharactersSet

        every { favoriteCharactersDao.getIds() } returns favoriteCharactersList

        val ramPage = mockk<RamPage<RamCharacter>>()
        every { transformer.characters(pageResponse, favoriteCharactersSet) } returns ramPage

        subject.invoke(0) shouldBe Result.success(ramPage)
    }

    @Test
    fun `verify exception thrown by repository is handled`() = runTest {
        val error = Exception("oops")
        coEvery { ramRepository.fetchCharacters(0) } throws error

        subject.invoke(0) shouldBe Result.failure(error)
    }

    @Test
    fun `verify exception thrown by favorites dao is handled`() = runTest {
        val pageResponse = mockk<PageResponse<CharacterFragment>>()
        coEvery { ramRepository.fetchCharacters(0) } returns pageResponse

        val error = Exception("oops")
        every { favoriteCharactersDao.getIds() } throws error

        subject.invoke(0) shouldBe Result.failure(error)
    }

    @Test
    fun `verify exception thrown by mapToSet is handled`() = runTest {
        mockkStatic("com.vkondrav.ram.room.Utils")

        val pageResponse = mockk<PageResponse<CharacterFragment>>()
        coEvery { ramRepository.fetchCharacters(0) } returns pageResponse

        val favoriteCharactersList = mockk<Flow<List<String>>>()

        val error = Exception("oops")
        every { favoriteCharactersList.mapToSet() } throws error
        every { favoriteCharactersDao.getIds() } returns favoriteCharactersList

        subject.invoke(0) shouldBe Result.failure(error)
    }

    @Test
    fun `verify exception thrown by transformer is handled`() = runTest {
        mockkStatic("com.vkondrav.ram.room.Utils")

        val pageResponse = mockk<PageResponse<CharacterFragment>>()
        coEvery { ramRepository.fetchCharacters(0) } returns pageResponse

        val favoriteCharactersList = mockk<Flow<List<String>>>()
        val favoriteCharactersSet = mockk<Flow<Set<String>>>()
        every { favoriteCharactersList.mapToSet() } returns favoriteCharactersSet

        every { favoriteCharactersDao.getIds() } returns favoriteCharactersList

        val error = Exception("oops")
        every { transformer.characters(pageResponse, favoriteCharactersSet) } throws error

        subject.invoke(0) shouldBe Result.failure(error)
    }
}
