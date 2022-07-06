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
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }

    lint {
        warningsAsErrors = true
    }

    tasks.withType<KotlinCompile> {

        val optIns = listOf(
            Experimental.coroutines,
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }
}

dependencies {
    api(Libs.Compose.ui)
    api(Libs.Compose.uiTooling)
    api(Libs.Compose.foundation)

    implementation(Libs.Compose.material)

    implementation(Libs.AndroidX.pagingRuntime)
    implementation(Libs.AndroidX.pagingCompose)

    implementation(Libs.Timber.core)
}
