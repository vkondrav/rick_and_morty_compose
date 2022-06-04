package com.vkondrav.playground.room.ram

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteLocationsDao {
    @Query("SELECT * FROM favorite_location")
    suspend fun getAll(): List<FavoriteLocation>

    @Query("SELECT id FROM favorite_location")
    suspend fun getIds(): List<String>

    @Insert
    suspend fun insert(favoriteLocation: FavoriteLocation)

    @Query("DELETE FROM favorite_location WHERE id = :id")
    suspend fun delete(id: String)
}
