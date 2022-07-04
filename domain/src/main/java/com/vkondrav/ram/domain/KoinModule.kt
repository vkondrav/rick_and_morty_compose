package com.vkondrav.ram.domain

import org.koin.dsl.module

val domainModule = module {
    factory {
        RamCharacter.Adapter
    }
    factory {
        RamEpisode.Adapter
    }
    factory {
        RamLocation.Adapter
    }
    factory {
        RamCharacterDetails.Adapter(
            characterAdapter = get(),
            episodeAdapter = get(),
            locationAdapter = get(),
        )
    }
    factory {
        RamEpisodeDetails.Adapter(
            episodeAdapter = get(),
            characterAdapter = get(),
        )
    }
    factory {
        RamLocationDetails.Adapter(
            characterAdapter = get(),
            locationAdapter = get(),
        )
    }
    factory {
        RamPage.Adapter(
            characterAdapter = get(),
            episodeAdapter = get(),
            locationAdapter = get(),
        )
    }
}
