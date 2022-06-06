package com.vkondrav.playground.di

import com.vkondrav.playground.domain.RamCharacter
import com.vkondrav.playground.domain.RamCharacterDetails
import com.vkondrav.playground.domain.RamEpisode
import com.vkondrav.playground.domain.RamEpisodeDetails
import com.vkondrav.playground.domain.RamLocation
import com.vkondrav.playground.domain.RamLocationDetails
import org.koin.dsl.module

val domainModule = module {
    factory {
        RamCharacter.SourceTransformer
    }
    factory {
        RamEpisode.SourceTransformer
    }
    factory {
        RamLocation.SourceTransformer
    }
    factory {
        RamCharacterDetails.SourceTransformer(
            characterSourceTransformer = get(),
            episodeSourceTransformer = get(),
            locationSourceTransformer = get(),
        )
    }
    factory {
        RamEpisodeDetails.SourceTransformer(
            episodeSourceTransformer = get(),
            characterSourceTransformer = get(),
        )
    }
    factory {
        RamLocationDetails.SourceTransformer(
            characterSourceTransformer = get(),
            locationSourceTransformer = get(),
        )
    }
}
