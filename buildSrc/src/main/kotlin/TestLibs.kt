object TestLibs {
    object JUnit {
        private const val version = "4.13.2"
        const val core = "junit:junit:$version"
    }

    object Compose {
        private const val base = "androidx.compose.ui"
        private const val version = "1.0.5"
        const val jUnit = "$base:ui-test-junit4:$version"
        const val manifest = "$base:ui-test-manifest:$version"
    }

    object Robolectric {
        private const val version = "4.7.3"
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
}
