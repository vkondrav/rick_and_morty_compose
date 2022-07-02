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

    tasks.koverXmlReport {
        excludes = listOf(
            "com.vkondrav.ram.test.*",
        )
    }
}

dependencies {

    implementation(TestLibs.JUnit.core)
    implementation(TestLibs.Compose.jUnit)
    implementation(TestLibs.Robolectric.core)
    implementation(TestLibs.Kotest.assertions)
    implementation(TestLibs.ArchCore.testing)
}
