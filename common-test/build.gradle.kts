plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.ram.test"

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

    lint {
        warningsAsErrors = true
    }
}

dependencies {
    implementation(TestLibs.Compose.jUnit)
    implementation(TestLibs.Robolectric.core)
    implementation(TestLibs.ArchCore.testing)

    api(TestLibs.JUnit.core)
    api(TestLibs.Kotest.assertions)
}
