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
        const val version = "${Libs.kotlinVersion}-1.0.5"
        const val base = "com.google.devtools.ksp"
    }

    object Detekt {
        const val version = "1.21.0-RC1"
        const val base = "io.gitlab.arturbosch.detekt"
        const val formatting = "$base:detekt-formatting:$version"
    }
}
