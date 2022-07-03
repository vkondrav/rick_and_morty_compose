package com.vkondrav.ram.domain

import org.koin.dsl.module

val domainModule = module {
    factory {
        RamCharacter.SourceConstructor
    }
    factory {
        RamEpisode.SourceConstructor
    }
    factory {
        RamLocation.SourceConstructor
    }
    factory {
        RamCharacterDetails.SourceConstructor(
            characterSourceConstructor = get(),
            episodeSourceConstructor = get(),
            locationSourceConstructor = get(),
        )
    }
    factory {
        RamEpisodeDetails.SourceConstructor(
            episodeSourceConstructor = get(),
            characterSourceConstructor = get(),
        )
    }
    factory {
        RamLocationDetails.SourceConstructor(
            characterSourceConstructor = get(),
            locationSourceConstructor = get(),
        )
    }
    factory {
        RamPage.SourceConstructor(
            characterSourceConstructor = get(),
            episodeSourceConstructor = get(),
            locationSourceConstructor = get(),
        )
    }
}
