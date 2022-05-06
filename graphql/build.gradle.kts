plugins {
    library()
    kotlin()
    apollo()
}

android {
    namespace = "com.vkondrav.playground.graphql"

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

    apollo {
        packageName.set("com.vkondrav.graphql")
    }
}

dependencies {
    implementation(Libs.Apollo.runtime)
}