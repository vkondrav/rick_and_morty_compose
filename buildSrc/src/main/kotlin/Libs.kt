object Libs {
    const val kotlinVersion = "1.6.21"
    const val jvmTarget = "1.8"
    const val gradlePluginVersion = "1.5.21"
    const val androidVersion = "7.2.0"

    object Compose {
        private const val gr = "androidx.compose"
        const val version = "1.2.0-beta01"

        const val ui = "$gr.ui:ui:$version"
        const val uiTooling = "$gr.ui:ui-tooling:$version"
        const val foundation = "$gr.foundation:foundation:$version"
        const val material = "$gr.material:material:$version"
        const val iconsCore = "$gr.material:material-icons-core:$version"
        const val iconsExt = "$gr.material:material-icons-extended:$version"
        const val viewBinding = "$gr.ui:ui-viewbinding:$version"
    }

    object Lifecycle {
        private const val version = "2.4.0"
        private const val base = "androidx.lifecycle"

        const val lifecycles = "$base:lifecycle-runtime-ktx:$version"
    }

    object Activity {
        private const val version = "1.4.0"

        const val compose = "androidx.activity:activity-compose:$version"
    }

    object Navigation {
        private const val version = "2.4.2"

        const val compose = "androidx.navigation:navigation-compose:$version"
    }

    object Androidx {
        private const val base = "androidx"

        const val appcompat = "$base.appcompat:appcompat:1.4.1"
        const val coreSplashscreen = "$base.core:core-splashscreen:1.0.0-beta02"
        const val coreKtx = "$base.core:core-ktx:1.7.0"
        const val constrainLayoutCompose =
            "$base.constraintlayout:constraintlayout-compose:1.0.0-beta02"
    }

    object Koin {
        private const val version = "3.1.6"

        const val core = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }

    object Material {
        private const val version = "1.5.0"

        const val core = "com.google.android.material:material:$version"
    }

    object Accompanist {
        private const val version = "0.24.7-alpha"
        private const val base = "com.google.accompanist"

        const val navAnimation = "$base:accompanist-navigation-animation:$version"
        const val navMaterial = "$base:accompanist-navigation-material:$version"
        const val pager = "$base:accompanist-pager:$version"
        const val indicators = "$base:accompanist-pager-indicators:$version"
    }

    object Apollo {
        const val version = "3.3.0"
        const val packageName = "packageName"
        const val base = "com.apollographql.apollo3"

        const val runtime = "$base:apollo-runtime:$version"
        const val api = "$base:apollo-api:$version"
    }

    object Paging {
        private const val version = "3.1.1"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
        const val core = "androidx.paging:paging-runtime:$version"
    }

    object Timber {
        private const val version = "5.0.1"
        const val core = "com.jakewharton.timber:timber:$version"
    }

    object Detekt {
        const val version = "1.20.0"
        const val base = "io.gitlab.arturbosch.detekt"
        const val formatting = "$base:detekt-formatting:$version"
    }

    object Coil {
        private const val version = "2.1.0"
        const val core = "io.coil-kt:coil-compose:$version"
    }

    object Room {
        private const val base = "androidx.room:"
        private const val version = "2.4.2"
        const val runtime = "${base}room-runtime:$version"
        const val compiler = "${base}room-compiler:$version"
    }

    object Ksp {
        const val version = "$kotlinVersion-1.0.5"
        const val base = "com.google.devtools.ksp"
    }
}
