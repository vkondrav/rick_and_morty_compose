plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.playground.apollo"

    compileSdk = Build.compileSdk
    buildToolsVersion = Build.buildTools

    defaultConfig {
        minSdk = Build.minSkd
        targetSdk = Build.targetSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Libs.jvmTarget
    }
}

dependencies {
    api(Libs.Apollo.runtime)
    api(Libs.Koin.core)
}
