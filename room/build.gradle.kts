@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE

plugins {
    library()
    kotlin()
    ksp()
}

android {
    namespace = "com.vkondrav.ram.room"

    compileSdk = Build.compileSdk
    buildToolsVersion = Build.buildTools

    defaultConfig {
        minSdk = Build.minSkd
        targetSdk = Build.targetSdk

        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Libs.jvmTarget
    }

    lint {
        warningsAsErrors = true
    }

    kotlin {
        sourceSets.main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
        sourceSets.test {
            kotlin.srcDir("build/generated/ksp/test/kotlin")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-opt-in=${Experimental.optIns}"
    }

    with(tasks) { // Kover Config

        val koverExcludes = listOf(
            "*.BuildConfig",
            "*_Impl*",
        )

        koverHtmlReport {
            excludes = koverExcludes
        }

        koverXmlReport {
            excludes = koverExcludes
        }

        koverVerify {
            excludes = koverExcludes
            rule {
                name = "100% Coverage Rule"
                bound {
                    @SuppressWarnings("MagicNumber")
                    minValue = 100
                    valueType = COVERED_LINES_PERCENTAGE
                }
            }
        }
    }

    testOptions  {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    with(Libs.Room) {
        implementation(runtime)
        implementation(ktx)
        annotationProcessor(compiler)
        ksp(compiler)
    }

    implementation(Libs.Koin.core)
    implementation(Libs.Timber.core)

    testImplementation(project(Module.commonTest))
}
