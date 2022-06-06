package com.vkondrav.playground.domain.di

import com.vkondrav.playground.domain.RamCharacter
import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.domain.RamEpisode
import com.vkondrav.playground.domain.RamEpisodeDetails
import com.vkondrav.playground.domain.RamLocation
import com.vkondrav.playground.domain.RamLocationDetails
import com.vkondrav.playground.domain.RamPage
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
