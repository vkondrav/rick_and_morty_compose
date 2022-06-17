import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.ram.apollo"

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

    tasks.withType<KotlinCompile> {

        val optIns = listOf(
            "kotlinx.coroutines.ExperimentalCoroutinesApi",
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }
}

dependencies {

    api(Libs.Apollo.runtime)
    api(Libs.Apollo.cache)
    api(Libs.Koin.core)

    testImplementation(project(Module.commonTest))

    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.Apollo.mockServer)
    testImplementation(TestLibs.Coroutines.core)
    testImplementation(TestLibs.Kotest.assertions)
    testImplementation(TestLibs.Turbine.core)
}
