package com.vkondrav.playground.room.ram

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_character")
    fun getAllFavoriteCharacters(): List<FavoriteCharacter>

    @Insert
    fun insert(favoriteCharacter: FavoriteCharacter)

    @Delete
    fun delete(favoriteCharacter: FavoriteCharacter)
}