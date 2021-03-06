import org.gradle.kotlin.dsl.version

object SupportLibs {

    object LeakCanary {
        private const val version = "2.9.1"
        const val core = "com.squareup.leakcanary:leakcanary-android:$version"
    }

    object Kover {
        const val version = "0.5.0"
        const val base = "org.jetbrains.kotlinx.kover"
    }

    object Ksp {
        const val version = "${Libs.kotlinVersion}-1.0.6"
        const val base = "com.google.devtools.ksp"
    }

    object Detekt {
        const val version = "1.21.0-RC2"
        const val base = "io.gitlab.arturbosch.detekt"
        const val formatting = "$base:detekt-formatting:$version"
    }

    object TaskInfo {
        const val version = "1.4.0"
        const val base = "org.barfuin.gradle.taskinfo"
    }

    object GradleVersions {
        const val plugin = "com.github.ben-manes.versions"
        const val gradle = "com.github.ben-manes:gradle-versions-plugin:+"
    }

    object DependencyGraphGenerator {
        const val plugin = "com.vanniktech.dependency.graph.generator"
        const val gradle = "com.vanniktech:gradle-dependency-graph-generator-plugin:+"
    }

    object KTLint {
        const val version = "10.3.0"
        const val ktlintVersion = "0.45.2"
        const val base = "org.jlleitschuh.gradle.ktlint"
    }
}
