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
            Experimental.coroutines,
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }

    val koverExcludes = listOf(
        "*.BuildConfig",
    )

    tasks.koverXmlReport {
        excludes = koverExcludes
    }

    tasks.koverVerify {
        excludes = koverExcludes
        rule {
            name = "100% Coverage Rule"
            bound {
                @SuppressWarnings("MagicNumber")
                minValue = 100
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE
            }
        }
    }
}

dependencies {

    implementation(project(Module.commonUtil))

    api(Libs.Apollo.runtime)
    api(Libs.Apollo.cache)
    api(Libs.Koin.core)

    testImplementation(project(Module.commonTest))

    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.Apollo.mockServer)
    testImplementation(TestLibs.KotlinX.coroutines)
    testImplementation(TestLibs.Kotest.assertions)
    testImplementation(TestLibs.Turbine.core)
    testImplementation(TestLibs.MockK.core)
    testImplementation(TestLibs.Koin.core)
}
