import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import kotlinx.kover.api.CoverageEngine.INTELLIJ

plugins {
    detekt()
    kover()
    taskInfo()
    ktlint()
}

apply(plugin = SupportLibs.GradleVersions.plugin)
apply(plugin = SupportLibs.DependencyGraphGenerator.plugin)

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.Android.gradle)
        classpath(Plugins.Kotlin.gradle)
        classpath(Plugins.Gradle.gradle)
        classpath(SupportLibs.GradleVersions.gradle)
        classpath(SupportLibs.DependencyGraphGenerator.gradle)
    }
}

allprojects {
    apply(plugin = SupportLibs.Detekt.base)
    apply(plugin = SupportLibs.KTLint.base)

    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }

    detekt {
        source = objects.fileCollection().from(
            DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
            DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
        )
    }

    dependencies {
        detektPlugins(SupportLibs.Detekt.formatting)
    }

    ktlint {
        android.set(true)
        version.set(SupportLibs.KTLint.ktlintVersion)
    }
}

// DETEKT
val analysisDir = file(projectDir)
val configFile = file("$rootDir/config/detekt/detekt.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs the whole project at once."
    parallel = true
    autoCorrect = false
    setSource(analysisDir)
    config.setFrom(listOf(configFile))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    reports {
        xml.required.set(false)
        html.required.set(false)
        txt.required.set(false)
    }
}

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats whole project."
    parallel = true
    autoCorrect = true
    setSource(analysisDir)
    config.setFrom(listOf(configFile))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    reports {
        xml.required.set(false)
        html.required.set(false)
        txt.required.set(false)
    }
}

// KOVER
kover {
    coverageEngine.set(INTELLIJ)
}
