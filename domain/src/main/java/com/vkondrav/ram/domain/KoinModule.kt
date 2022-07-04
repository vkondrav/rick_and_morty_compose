package com.vkondrav.ram.domain

import org.koin.dsl.module

val domainModule = module {
    factory {
        RamCharacter.Factory
    }
    factory {
        RamCharacter.Adapter
    }
    factory {
        RamEpisode.Factory
    }
    factory {
        RamEpisode.Adapter
    }
    factory {
        RamLocation.Factory
    }
    factory {
        RamLocation.Adapter
    }
    factory {
        RamCharacterDetails.Factory(
            characterFactory = get(),
            episodeFactory = get(),
            locationFactory = get(),
        )
    }
    factory {
        RamEpisodeDetails.Factory(
            episodeFactory = get(),
            characterFactory = get(),
        )
    }
    factory {
        RamLocationDetails.Factory(
            characterFactory = get(),
            locationFactory = get(),
        )
    }
    factory {
        RamPage.Factory(
            characterFactory = get(),
            episodeFactory = get(),
            locationFactory = get(),
        )
    }
}
