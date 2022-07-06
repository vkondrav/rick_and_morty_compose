import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE

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
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
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

        val optIns = listOf(
            Experimental.pager,
            Experimental.animation,
            Experimental.navigation,
            Experimental.material,
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

    androidResources {
        additionalParameters.add("--warn-manifest-validation")
    }
}

dependencies {

    implementation(project(Module.domain))
    implementation(project(Module.commonUtil))
    implementation(project(Module.commonUi))
    implementation(project(Module.navigation))
    implementation(project(Module.collapsableDrawer))
    implementation(project(Module.characterAll))
    implementation(project(Module.snackbar))

    implementation(Libs.AndroidX.ktx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintLayoutCompose)
    implementation(Libs.AndroidX.splashscreen)
    implementation(Libs.AndroidX.lifecycle)
    implementation(Libs.AndroidX.pagingRuntime)
    implementation(Libs.AndroidX.activityCompose)

    implementation(Libs.Material.core)

    implementation(Libs.Compose.material)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExt)
    implementation(Libs.Compose.viewBinding)

    implementation(Libs.Accompanist.pager)
    implementation(Libs.Accompanist.indicators)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.compose)

    implementation(Libs.Timber.core)

    implementation(Libs.Coil.core)

    implementation(Libs.DataStore.core)

    testImplementation(project(Module.commonTest))

    testImplementation(TestLibs.Robolectric.core)
    testImplementation(TestLibs.Compose.jUnit)
    testImplementation(TestLibs.MockK.core)
    testImplementation(TestLibs.Koin.core)
    testImplementation(TestLibs.Turbine.core)

    testApi(project(Module.apollo))

    debugImplementation(TestLibs.Compose.manifest)
    debugImplementation(SupportLibs.LeakCanary.core)
}
