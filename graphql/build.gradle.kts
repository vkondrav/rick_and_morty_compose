@file:Suppress("UnstableApiUsage")

import kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    library()
    kotlin()
    apollo()
}

android {
    namespace = "com.vkondrav.ram.graphql"

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

    apollo {
        packageName.set("com.vkondrav.ram.graphql.generated")
        generateKotlinModels.set(true)
        codegenModels.set("operationBased")
        generateAsInternal.set(false)
        generateTestBuilders.set(true)
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
            "*.generated.*",
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
    api(project(Module.apollo))

    implementation(Libs.Apollo.api)
    implementation(Libs.Koin.core)
    implementation(Libs.Timber.core)

    testImplementation(project(Module.commonTest))
}
