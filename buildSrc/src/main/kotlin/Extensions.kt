import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version

fun org.gradle.plugin.use.PluginDependenciesSpec.application() = id("com.android.application")

fun org.gradle.plugin.use.PluginDependenciesSpec.library() = id("com.android.library")

fun org.gradle.plugin.use.PluginDependenciesSpec.kotlin() = kotlin("android")

fun org.gradle.plugin.use.PluginDependenciesSpec.apollo() =
    id(Libs.Apollo.base).version(Libs.Apollo.version)

fun org.gradle.plugin.use.PluginDependenciesSpec.detekt() =
    id(SupportLibs.Detekt.base).version(SupportLibs.Detekt.version)

fun org.gradle.plugin.use.PluginDependenciesSpec.ksp() =
    id(SupportLibs.Ksp.base).version(SupportLibs.Ksp.version)

fun org.gradle.plugin.use.PluginDependenciesSpec.parcelize() = id("kotlin-parcelize")

fun org.gradle.plugin.use.PluginDependenciesSpec.kover() =
    id(SupportLibs.Kover.base) version SupportLibs.Kover.version

fun org.gradle.plugin.use.PluginDependenciesSpec.taskInfo() =
    id(SupportLibs.TaskInfo.base) version SupportLibs.TaskInfo.version

