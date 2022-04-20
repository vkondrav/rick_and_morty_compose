object Plugins {
    object Ktlint {
        const val version = "10.2.1"
        const val id = "org.jlleitschuh.gradle.ktlint"
    }

    object Kotlin {
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}"
    }

    object Android {
        const val gradle =
            "com.android.tools.build:gradle:${Libs.androidVersion}"
    }

    object Gradle {
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.gradlePluginVersion}"
    }
}