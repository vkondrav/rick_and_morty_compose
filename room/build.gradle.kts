plugins {
    library()
    kotlin()
    ksp()
}

android {
    namespace = "com.vkondrav.ram.room"

    compileSdk = Build.compileSdk
    buildToolsVersion = Build.buildTools

    defaultConfig {
        minSdk = Build.minSkd
        targetSdk = Build.targetSdk

        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
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

    kotlin {
        sourceSets.main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
        sourceSets.test {
            kotlin.srcDir("build/generated/ksp/test/kotlin")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {

        val optIns = listOf(
            "kotlinx.coroutines.ExperimentalCoroutinesApi",
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }

    testOptions  {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(Libs.Koin.core)
    implementation(Libs.Timber.core)

    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    annotationProcessor(Libs.Room.compiler)

    ksp(Libs.Room.compiler)

    testImplementation(project(Module.commonTest))
    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.Robolectric.core)
    testImplementation(TestLibs.KotlinX.coroutines)
    testImplementation(TestLibs.Turbine.core)
    testImplementation(TestLibs.Kotest.assertions)
}
