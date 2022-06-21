object Libs {
    const val kotlinVersion = "1.6.21"
    const val jvmTarget = "1.8"
    const val gradlePluginVersion = "1.5.21"
    const val gradleVersion = "7.2.0"

    object Compose {
        private const val base = "androidx.compose"

        @SuppressWarnings("version used in app/build.gradle.kts and cannot be private")
        const val version = "1.2.0-beta03"

        const val ui = "$base.ui:ui:$version"
        const val uiTooling = "$base.ui:ui-tooling:$version"
        const val foundation = "$base.foundation:foundation:$version"
        const val material = "$base.material:material:$version"
        const val iconsCore = "$base.material:material-icons-core:$version"
        const val iconsExt = "$base.material:material-icons-extended:$version"
        const val viewBinding = "$base.ui:ui-viewbinding:$version"
    }

    object AndroidX {
        private const val base = "androidx"

        const val appcompat = "$base.appcompat:appcompat:1.6.0-alpha04"
        const val splashscreen = "$base.core:core-splashscreen:1.0.0-rc01"
        const val ktx = "$base.core:core-ktx:1.9.0-alpha04"
        const val constraintLayoutCompose = base +
                ".constraintlayout:constraintlayout-compose:1.1.0-alpha02"
        const val lifecycle = "$base.lifecycle:lifecycle-runtime-ktx:2.5.0-rc01"
        const val navigationCompose = "$base.navigation:navigation-compose:2.5.0-rc01"
        const val pagingCompose = "$base.paging:paging-compose:1.0.0-alpha15"
        const val pagingRuntime = "$base.paging:paging-runtime:3.2.0-alpha01"
        const val activityCompose = "$base.activity:activity-compose:1.6.0-alpha03"
    }

    object Koin {
        const val base = "io.insert-koin"
        const val version = "3.2.0"

        const val core = "${base}:koin-android:$version"
        const val compose = "${base}:koin-androidx-compose:$version"
    }

    object Material {
        private const val version = "1.7.0-alpha02"

        const val core = "com.google.android.material:material:$version"
    }

    object Accompanist {
        private const val version = "0.24.10-beta"
        private const val base = "com.google.accompanist"

        const val navAnimation = "$base:accompanist-navigation-animation:$version"
        const val navMaterial = "$base:accompanist-navigation-material:$version"
        const val pager = "$base:accompanist-pager:$version"
        const val indicators = "$base:accompanist-pager-indicators:$version"
    }

    object Apollo {
        const val version = "3.3.0"
        const val base = "com.apollographql.apollo3"

        const val runtime = "$base:apollo-runtime:$version"
        const val api = "$base:apollo-api:$version"
        const val cache = "$base:apollo-normalized-cache:$version"
    }

    object Timber {
        private const val version = "5.0.1"
        const val core = "com.jakewharton.timber:timber:$version"
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
        const val ktx = "${base}room-ktx:$version"
    }

    object KotlinX {
        private const val version = "1.6.3"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }
}
