object Plugins {
    object Kotlin {
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}"
    }

    object Android {
        const val gradle =
            "com.android.tools.build:gradle:${Libs.androidVersion}"
    }

    object Gradle {
        @SuppressWarnings("MemberNameEqualsClassName")
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.gradlePluginVersion}"
    }
}
