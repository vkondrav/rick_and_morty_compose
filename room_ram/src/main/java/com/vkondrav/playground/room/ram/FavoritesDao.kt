package com.vkondrav.playground.room.ram

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_character")
    suspend fun getCharacters(): List<FavoriteCharacter>

    @Query("SELECT id FROM favorite_character")
    suspend fun getCharacterIds(): List<String>

    @Insert
    suspend fun insertCharacter(favoriteCharacter: FavoriteCharacter)

    @Query("DELETE FROM favorite_character WHERE id = :id")
    suspend fun deleteCharacter(id: String)
}
