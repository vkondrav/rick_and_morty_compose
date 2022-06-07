object Plugins {
    object Kotlin {
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}"
    }

    object Android {
        const val gradle =
            "com.android.tools.build:gradle:${Libs.gradleVersion}"
    }

    object Gradle {
        @SuppressWarnings("MemberNameEqualsClassName")
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.gradlePluginVersion}"
    }

    object DependencyUpdate {
        const val plugin = "com.github.ben-manes.versions"
        const val gradle = "com.github.ben-manes:gradle-versions-plugin:+"
    }
}
