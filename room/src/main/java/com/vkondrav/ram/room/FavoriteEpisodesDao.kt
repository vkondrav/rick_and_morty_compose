package com.vkondrav.ram.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteEpisodesDao {
    @Query("SELECT * FROM favorite_episode")
    fun getAll(): Flow<List<FavoriteEpisode>>

    @Query("SELECT id FROM favorite_episode")
    fun getIds(): Flow<List<String>>

    @Insert
    suspend fun insert(favoriteEpisode: FavoriteEpisode)

    @Query("DELETE FROM favorite_episode WHERE id = :id")
    suspend fun delete(id: String)
}
