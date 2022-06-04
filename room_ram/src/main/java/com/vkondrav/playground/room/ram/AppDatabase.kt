package com.vkondrav.playground.room.ram

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FavoriteCharacter::class,
        FavoriteLocation::class,
    ],
    version = 2,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharactersDao(): FavoriteCharactersDao
    abstract fun favoriteLocationsDoa(): FavoriteLocationsDao
}
