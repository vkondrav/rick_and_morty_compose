import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.ram.domain"

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
            Experimental.coroutines,
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }

    tasks.koverXmlReport {
        excludes = listOf(
            "*.BuildConfig",
        )
    }
}

dependencies {

    api(project(Module.graphql))
    api(project(Module.room))
    api(project(Module.dataStore))

    implementation(Libs.Koin.core)
    implementation(Libs.Timber.core)

    testImplementation(project(Module.commonTest))
    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.KotlinX.coroutines)
    testImplementation(TestLibs.Turbine.core)
    testImplementation(TestLibs.Kotest.assertions)
    testImplementation(TestLibs.MockK.core)
}
