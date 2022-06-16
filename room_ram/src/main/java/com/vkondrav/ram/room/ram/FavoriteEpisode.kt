package com.vkondrav.ram.room.ram

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_episode")
data class FavoriteEpisode(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "air_date") val airDate: String?,
)
