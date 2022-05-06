import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependencySpec

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
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.gradlePluginVersion}"
    }

    object Apollo {
        const val apollo = "com.apollographql.apollo3:${Libs.Apollo.version}"
    }
}