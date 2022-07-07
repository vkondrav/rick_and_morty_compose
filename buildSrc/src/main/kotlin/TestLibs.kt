object TestLibs {
    object JUnit {
        private const val version = "4.13.2"
        const val core = "junit:junit:$version"
    }

    object Compose {
        private const val base = "androidx.compose.ui"
        private const val version = Libs.Compose.version
        const val jUnit = "$base:ui-test-junit4:$version"
        const val manifest = "$base:ui-test-manifest:$version"
    }

    object Robolectric {
        private const val version = "4.8.1"
        const val core = "org.robolectric:robolectric:$version"
    }

    object Kotest {
        private const val version = "5.3.2"
        const val assertions = "io.kotest:kotest-assertions-core:$version"
    }

    object Apollo {
        private const val version = Libs.Apollo.version
        private const val base = Libs.Apollo.base

        const val mockServer = "$base:apollo-mockserver:$version"
    }

    object KotlinX {
        private const val version = Libs.KotlinX.version
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
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

    object MockK {
        private const val version = "1.12.4"
        const val core = "io.mockk:mockk:$version"
    }

    object ArchCore {
        private const val version = "2.1.0"
        const val testing = "androidx.arch.core:core-testing:$version"
    }
}
