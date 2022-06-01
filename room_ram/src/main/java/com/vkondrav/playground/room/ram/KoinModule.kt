package com.vkondrav.playground.room.ram

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module

val roomModule = module {
    single(DATABASE_NAME) { "ram_database" }
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            get(DATABASE_NAME)
        ).build()
    }
    factory {
        get<AppDatabase>().favoritesDao()
    }
}

val DATABASE_NAME = named("DATABASE_NAME")