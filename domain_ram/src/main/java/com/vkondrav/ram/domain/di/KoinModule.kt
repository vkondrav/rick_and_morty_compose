package com.vkondrav.ram.domain.di

import com.vkondrav.ram.domain.RamCharacter
import com.vkondrav.ram.domain.RamCharacterDetails
import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.domain.RamEpisodeDetails
import com.vkondrav.ram.domain.RamLocation
import com.vkondrav.ram.domain.RamLocationDetails
import com.vkondrav.ram.domain.RamPage
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
