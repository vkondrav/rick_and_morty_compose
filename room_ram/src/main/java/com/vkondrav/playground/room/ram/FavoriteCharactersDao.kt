package com.vkondrav.playground.room.ram

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteCharactersDao {
    @Query("SELECT * FROM favorite_character")
    suspend fun getAll(): List<FavoriteCharacter>

    @Query("SELECT id FROM favorite_character")
    suspend fun getIds(): List<String>

    @Insert
    suspend fun insert(favoriteCharacter: FavoriteCharacter)

    @Query("DELETE FROM favorite_character WHERE id = :id")
    suspend fun delete(id: String)
}
