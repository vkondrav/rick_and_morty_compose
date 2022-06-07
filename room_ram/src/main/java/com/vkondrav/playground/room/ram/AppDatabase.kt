package com.vkondrav.playground.room.ram

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FavoriteCharacter::class,
        FavoriteLocation::class,
        FavoriteEpisode::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharactersDao(): FavoriteCharactersDao
    abstract fun favoriteLocationsDoa(): FavoriteLocationsDao
    abstract fun favoriteEpisodeDao(): FavoriteEpisodesDao
}
