package com.vkondrav.ram.room

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            get(DATABASE_NAME),
        ).fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }
    factory {
        get<AppDatabase>().favoriteCharactersDao()
    }
    factory {
        get<AppDatabase>().favoriteLocationsDoa()
    }
    factory {
        get<AppDatabase>().favoriteEpisodeDao()
    }
}

val DATABASE_NAME = named("DATABASE_NAME")
