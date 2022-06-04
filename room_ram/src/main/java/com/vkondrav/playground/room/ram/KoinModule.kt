package com.vkondrav.playground.room.ram

import androidx.room.Room
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "ram_database",
        ).build()
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
