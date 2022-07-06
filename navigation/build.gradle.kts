import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE

plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.ram.navigation"

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
            Experimental.navigation,
            Experimental.animation,
            Experimental.coroutines,
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
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
                    minValue = 0 //TODO: update to 100 when ready
                    valueType = COVERED_LINES_PERCENTAGE
                }
            }
        }
    }
}

dependencies {
    implementation(project(Module.commonUtil))
    implementation(project(Module.commonUi))

    api(Libs.AndroidX.navigationCompose)
    api(Libs.Accompanist.navAnimation)
    implementation(Libs.Accompanist.navMaterial)
    implementation(Libs.Koin.core)

    testImplementation(project(Module.commonTest))

    testImplementation(TestLibs.MockK.core)
    testImplementation(TestLibs.KotlinX.coroutines)
    testImplementation(TestLibs.Koin.core)
    testImplementation(TestLibs.Turbine.core)
}
