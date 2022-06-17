import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version

fun org.gradle.plugin.use.PluginDependenciesSpec.application() = id("com.android.application")

fun org.gradle.plugin.use.PluginDependenciesSpec.library() = id("com.android.library")

fun org.gradle.plugin.use.PluginDependenciesSpec.kotlin() = kotlin("android")

fun org.gradle.plugin.use.PluginDependenciesSpec.apollo() =
    id(Libs.Apollo.base).version(Libs.Apollo.version)

fun org.gradle.plugin.use.PluginDependenciesSpec.detekt() =
    id(Libs.Detekt.base).version(Libs.Detekt.version)

fun org.gradle.plugin.use.PluginDependenciesSpec.ksp() =
    id(Libs.Ksp.base).version(Libs.Ksp.version)

fun org.gradle.plugin.use.PluginDependenciesSpec.parcelize() = id("kotlin-parcelize")

fun org.gradle.plugin.use.PluginDependenciesSpec.kover() =
    id(Libs.Kover.base) version Libs.Kover.version

