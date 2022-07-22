@file:Suppress("UnstableApiUsage")

plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.ram.snackbar"

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
                    valueType = kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE
                }
            }
        }
    }
}

dependencies {

    with(Module) {
        implementation(project(commonUtil))
        implementation(project(commonUi))
    }

    with(Libs.Compose) {
        implementation(material)
        implementation(iconsCore)
        implementation(iconsExt)
    }

    with(Libs.Koin) {
        implementation(core)
        implementation(compose)
    }

    testImplementation(project(Module.commonTest))
}
