package com.vkondrav.ram.room.ram

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteLocationsDao {
    @Query("SELECT * FROM favorite_location")
    fun getAll(): Flow<List<FavoriteLocation>>

    @Query("SELECT id FROM favorite_location")
    fun getIds(): Flow<List<String>>

    @Insert
    suspend fun insert(favoriteLocation: FavoriteLocation)

    @Query("DELETE FROM favorite_location WHERE id = :id")
    suspend fun delete(id: String)
}
