package com.vkondrav.playground.graphql.ram

import com.vkondrav.apollo.Service
import com.vkondrav.graphql.ram.CharactersQuery
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

interface RamRepository {
    suspend fun fetchCharacters(page: Int): List<RamCharacter>
}

internal class RamRepositoryImp(private val service: Service) : RamRepository {
    override suspend fun fetchCharacters(page: Int): List<RamCharacter> =
        service.query(CharactersQuery(page)).data?.characters?.results?.mapNotNull {
            it?.name?.run { RamCharacter(this) }
        } ?: emptyList()
}