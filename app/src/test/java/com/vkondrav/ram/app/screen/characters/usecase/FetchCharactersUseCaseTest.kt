package com.vkondrav.ram.app.screen.characters.usecase

import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.graphql.ram.fragment.CharacterFragment
import com.vkondrav.ram.graphql.ram.RamRepository
import com.vkondrav.ram.graphql.ram.domain.RamCharacterTransformer
import com.vkondrav.ram.graphql.ram.error.InvalidDataException
import com.vkondrav.ram.room.ram.FavoriteCharactersDao
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import timber.log.Timber

class FetchCharactersUseCaseTest {
    private lateinit var ramRepository: RamRepository
    private lateinit var favoriteCharactersDao: FavoriteCharactersDao
    private lateinit var transformer: RamCharacterTransformer
    private lateinit var subject: FetchCharactersUseCase

    private lateinit var testTree: Timber.Tree

    @Before
    fun setUp() {
        testTree = mock()
        Timber.plant(testTree)

        ramRepository = mock()
        favoriteCharactersDao = mock()
        transformer = mock()
        subject = FetchCharactersUseCase(ramRepository, favoriteCharactersDao, transformer)
    }

    @After
    fun tearDown() {
        Timber.uproot(testTree)
    }

    @Test
    fun `verify favoritesDao getCharacterIds error result in error result`() = runTest {
        whenever(favoriteCharactersDao.getIds()) doAnswer { throw InvalidDataException("Oh no") }
        shouldThrow<Exception> {
            subject(0).getOrThrow()
        }.message shouldBe "Oh no"
    }

    @Test
    fun `verify ramRepository fetchCharacters error results in error result`() = runTest {
        whenever(favoriteCharactersDao.getIds()) doReturn emptyList()
        whenever(ramRepository.fetchCharacters(0)) doAnswer { throw InvalidDataException("Oh no") }
        shouldThrow<Exception> {
            subject(0).getOrThrow()
        }.message shouldBe "Oh no"
    }

    @Test
    fun `verify transformer errors result in empty list`() = runTest {
        val fragment = mock<CharacterFragment>()
        val result = mock<CharactersQuery.Result> {
            on { characterFragment } doReturn fragment
        }
        val exception = Exception("Oh no")

        whenever(favoriteCharactersDao.getIds()) doReturn emptyList()
        whenever(ramRepository.fetchCharacters(0)) doReturn listOf(result)
        whenever(transformer.invoke(any(), any())) doAnswer { throw exception }

        subject(0).getOrThrow() shouldBe emptyList()
        verify(testTree).e(exception)
    }

    @Test
    fun `verify happy path transformation`() = runTest {
        val favorites = mock<List<String>>()
        val fragment = mock<CharacterFragment>()
        val result = mock<CharactersQuery.Result> {
            on { characterFragment } doReturn fragment
        }

        whenever(favoriteCharactersDao.getIds()) doReturn favorites
        whenever(ramRepository.fetchCharacters(0)) doReturn listOf(result)

        whenever(transformer.invoke(fragment, favorites.toSet())) doReturn mock()
    }
}
