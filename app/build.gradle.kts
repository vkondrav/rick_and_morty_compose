import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    buildToolsVersion = "31.0.0"

    defaultConfig {
        applicationId = "com.vkondrav.playground.app"
        minSdk = 22
        targetSdk = 31
        versionCode = 1
        versionName = "0.0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs +=
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi"
    }
}

dependencies {
    implementation(Libs.Androidx.coreKtx)
    implementation(Libs.Androidx.appcompat)
    implementation(Libs.Androidx.constrainLayoutCompose)
    implementation(Libs.Androidx.coreSplashscreen)

    implementation(Libs.Material.material)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.uiTooling)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExt)
    implementation(Libs.Compose.viewBinding)

    implementation(Libs.Navigation.compose)

    implementation(Libs.Accompanist.navAnimation)
    implementation(Libs.Accompanist.pager)
    implementation(Libs.Accompanist.indicators)

    implementation(Libs.Lifecycle.lifecycles)

    implementation(Libs.Activity.compose)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.compose)

    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.Robolectric.core)
    testImplementation(TestLibs.Compose.jUnit)
    testImplementation(TestLibs.Mockito.core)

    debugImplementation(TestLibs.Compose.manifest)
}
