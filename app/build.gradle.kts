@file:Suppress("UnstableApiUsage")

import kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application()
    kotlin()
    parcelize()
}

android {
    compileSdk = Build.compileSdk
    buildToolsVersion = Build.buildTools

    defaultConfig {
        applicationId = "com.vkondrav.ram.app"

        minSdk = Build.minSkd
        targetSdk = Build.targetSdk

        versionCode = Build.Version.code
        versionName = Build.Version.name

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
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

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    lint {
        warningsAsErrors = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
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

    androidResources {
        additionalParameters.add("--warn-manifest-validation")
    }
}

dependencies {

    with(Module) {
        implementation(project(commonUtil))
        implementation(project(commonUi))
        implementation(project(domain))
        implementation(project(snackbar))
        implementation(project(drawer))
        implementation(project(themeController))
        implementation(project(characterAll))
        implementation(project(characterDetails))
        implementation(project(characterFavorite))
        implementation(project(episodeAll))
        implementation(project(episodeDetails))
        implementation(project(episodeFavorite))
        implementation(project(locationAll))
        implementation(project(locationDetails))
        implementation(project(locationFavorite))
    }

    implementation(Libs.AndroidX.splashscreen)
    implementation(Libs.Material.core)

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

    debugImplementation(TestLibs.Compose.manifest)
    debugImplementation(SupportLibs.LeakCanary.core)
}
