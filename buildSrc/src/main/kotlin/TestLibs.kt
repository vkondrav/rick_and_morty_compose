object TestLibs {
    object JUnit {
        private const val version = "4.13.2"
        const val core = "junit:junit:$version"
    }

    object Compose {
        private const val base = "androidx.compose.ui"
        private const val version = "1.2.0-beta03"
        const val jUnit = "$base:ui-test-junit4:$version"
        const val manifest = "$base:ui-test-manifest:$version"
    }

    object Robolectric {
        private const val version = "4.8.1"
        const val core = "org.robolectric:robolectric:$version"
    }

    object Mockito {
        private const val version = "4.0.0"
        const val core = "org.mockito.kotlin:mockito-kotlin:$version"
    }

    object Kotest {
        private const val version = "5.3.0"
        const val assertions = "io.kotest:kotest-assertions-core:$version"
    }

    object Apollo {
        private const val version = Libs.Apollo.version
        private const val base = Libs.Apollo.base

        const val mockServer = "$base:apollo-mockserver:$version"
    }

    object Coroutines {
        private const val version = "1.6.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Turbine {
        private const val version = "0.8.0"
        const val core = "app.cash.turbine:turbine:$version"
    }

    object Koin {

        private const val base = Libs.Koin.base
        private const val version = Libs.Koin.version

        const val core = "$base:koin-test:$version"
    }
}
