plugins {
    library()
    kotlin()
}

android {
    namespace = "com.vkondrav.playground.domain"

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

    api(project(Module.graphqlRam))
    implementation(project(Module.apollo))
    implementation(Libs.Koin.core)

    implementation(Libs.Timber.core)
}
