import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application()
    kotlin()
}

android {
    compileSdk = Build.compileSdk
    buildToolsVersion = Build.buildTools

    defaultConfig {
        applicationId = "com.vkondrav.playground.app"

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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
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
            "com.google.accompanist.pager.ExperimentalPagerApi",
            "androidx.compose.animation.ExperimentalAnimationApi",
            "com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "androidx.compose.material.ExperimentalMaterialApi",
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }
}

dependencies {

    implementation(project(Module.domainRam))
    implementation(project(Module.roomRam))

    implementation(Libs.Androidx.coreKtx)
    implementation(Libs.Androidx.appcompat)
    implementation(Libs.Androidx.constrainLayoutCompose)
    implementation(Libs.Androidx.coreSplashscreen)

    implementation(Libs.Material.core)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.uiTooling)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExt)
    implementation(Libs.Compose.viewBinding)

    implementation(Libs.Paging.core)
    implementation(Libs.Paging.compose)

    implementation(Libs.Navigation.compose)

    implementation(Libs.Accompanist.navAnimation)
    implementation(Libs.Accompanist.navMaterial)
    implementation(Libs.Accompanist.pager)
    implementation(Libs.Accompanist.indicators)

    implementation(Libs.Lifecycle.lifecycles)

    implementation(Libs.Activity.compose)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.compose)

    implementation(Libs.Timber.core)

    implementation(Libs.Coil.core)

    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.Robolectric.core)
    testImplementation(TestLibs.Compose.jUnit)
    testImplementation(TestLibs.Mockito.core)
    testImplementation(TestLibs.Kotest.assertions)

    testApi(project(Module.apollo))

    debugImplementation(TestLibs.Compose.manifest)
}
