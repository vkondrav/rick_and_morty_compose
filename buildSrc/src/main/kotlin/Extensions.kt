import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.application() = id("com.android.application")

fun PluginDependenciesSpec.library() = id("com.android.library")

fun PluginDependenciesSpec.kotlin() = kotlin("android")

fun PluginDependenciesSpec.parcelize() = id("kotlin-parcelize")

fun PluginDependenciesSpec.apollo() =
    id(Libs.Apollo.base) version Libs.Apollo.version

fun PluginDependenciesSpec.detekt() =
    id(SupportLibs.Detekt.base) version SupportLibs.Detekt.version

fun PluginDependenciesSpec.ksp() =
    id(SupportLibs.Ksp.base) version SupportLibs.Ksp.version

fun PluginDependenciesSpec.kover() =
    id(SupportLibs.Kover.base) version SupportLibs.Kover.version

fun PluginDependenciesSpec.taskInfo() =
    id(SupportLibs.TaskInfo.base) version SupportLibs.TaskInfo.version

