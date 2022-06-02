package com.vkondrav.playground.room.ram

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCharacter::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}
