object Libs {
    const val kotlinVersion = "1.6.10"
    const val jvmTarget = "1.8"
    const val gradlePluginVersion = "1.5.21"
    const val androidVersion = "7.1.0"

    object Compose {
        private const val gr = "androidx.compose"
        const val version = "1.1.1"

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
        private const val gr = "androidx.lifecycle"

        const val lifecycles = "$gr:lifecycle-runtime-ktx:$version"
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
        private const val gr = "androidx"

        const val appcompat = "$gr.appcompat:appcompat:1.4.1"
        const val coreSplashscreen = "$gr.core:core-splashscreen:1.0.0-beta02"
        const val coreKtx = "$gr.core:core-ktx:1.7.0"
        const val constrainLayoutCompose =
            "$gr.constraintlayout:constraintlayout-compose:1.0.0-beta02"
    }

    object Koin {
        private const val version = "3.1.6"

        const val core = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }

    object Material {
        private const val version = "1.5.0"

        const val material = "com.google.android.material:material:$version"
    }

    object Accompanist {
        private const val version = "0.24.7-alpha"
        private const val gr = "com.google.accompanist"

        const val navAnimation = "$gr:accompanist-navigation-animation:$version"
        const val pager = "$gr:accompanist-pager:$version"
        const val indicators = "$gr:accompanist-pager-indicators:$version"
    }

    object Apollo {
        const val version = "3.3.0"
        const val packageName = "packageName"
        const val gr = "com.apollographql.apollo3"

        const val runtime = "$gr:apollo-runtime:$version"
        const val api = "$gr:apollo-api:$version"
    }

    object Paging {
        private const val version = "3.1.1"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
        const val core = "androidx.paging:paging-runtime:$version"
    }
}
