import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    library()
    kotlin()
    parcelize()
}

android {
    namespace = "com.vkondrav.ram.common.ui"

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
}

dependencies {
    api(Libs.Compose.ui)
    api(Libs.Compose.uiTooling)
    api(Libs.Compose.foundation)

    implementation(Libs.Compose.material)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExt)

    implementation(Libs.AndroidX.pagingRuntime)
    implementation(Libs.AndroidX.pagingCompose)

    implementation(Libs.Accompanist.pager)
    implementation(Libs.Accompanist.indicators)

    implementation(Libs.Timber.core)

    implementation(Libs.Coil.core)
}
