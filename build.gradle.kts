plugins {
    detekt()
}

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
    }
}

allprojects {
    apply(plugin = Libs.Detekt.gr)

    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }

    detekt {
        source = objects.fileCollection().from(
            io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
            io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
        )
    }

    dependencies {
        detektPlugins(Libs.Detekt.formatting)
    }
}

val analysisDir = file(projectDir)
val configFile = file("$rootDir/config/detekt/detekt.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

val detektAll by tasks.registering(io.gitlab.arturbosch.detekt.Detekt::class) {
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

val detektFormat by tasks.registering(io.gitlab.arturbosch.detekt.Detekt::class) {
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
