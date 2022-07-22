@file:Suppress("UnstableApiUsage")

import kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.ram.episode.common"

    compileSdk = Build.compileSdk
    buildToolsVersion = Build.buildTools

    defaultConfig {
        minSdk = Build.minSkd
        targetSdk = Build.targetSdk

        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.compiler
    }

    lint {
        warningsAsErrors = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-opt-in=${Experimental.optIns}"
    }

    with(tasks) { // Kover Config

        val koverExcludes = listOf(
            "*.BuildConfig",
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
                    minValue = 0 // TODO: update to 100 when ready
                    valueType = COVERED_LINES_PERCENTAGE
                }
            }
        }
    }
}

dependencies {
    with(Module) {
        implementation(project(commonUtil))
        implementation(project(commonUi))
        api(project(domain))
        api(project(navigation))
        api(project(snackbar))
    }

    with(Libs.Compose) {
        implementation(material)
        implementation(iconsCore)
        implementation(iconsExt)
    }
    implementation(Libs.AndroidX.constraintLayoutCompose)

    with(Libs.Koin) {
        implementation(core)
        implementation(compose)
    }

    implementation(Libs.AndroidX.pagingRuntime)

    testImplementation(project(Module.commonTest))
}
