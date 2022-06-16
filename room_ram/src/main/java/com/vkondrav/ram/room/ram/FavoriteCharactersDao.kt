package com.vkondrav.ram.room.ram

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharactersDao {
    @Query("SELECT * FROM favorite_character")
    fun getAll(): Flow<List<FavoriteCharacter>>

    @Query("SELECT id FROM favorite_character")
    fun getIds(): Flow<List<String>>

    @Insert
    suspend fun insert(favoriteCharacter: FavoriteCharacter)

    @Query("DELETE FROM favorite_character WHERE id = :id")
    suspend fun delete(id: String)
}
