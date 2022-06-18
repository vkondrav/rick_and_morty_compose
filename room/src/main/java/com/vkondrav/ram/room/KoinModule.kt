package com.vkondrav.ram.room

import androidx.room.Room
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "ram_database",
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
