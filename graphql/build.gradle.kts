plugins {
    library()
    kotlin()
    apollo()
}

android {
    namespace = "com.vkondrav.ram.graphql"

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

    apollo {
        packageName.set("com.vkondrav.graphql.ram")
        generateKotlinModels.set(true)
        codegenModels.set("operationBased")
        generateAsInternal.set(false)
        generateTestBuilders.set(true)
    }

    lint {
        warningsAsErrors = true
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {

        val optIns = listOf(
            "kotlinx.coroutines.ExperimentalCoroutinesApi",
            "com.apollographql.apollo3.annotations.ApolloExperimental",
        ).joinToString(separator = ",")

        kotlinOptions.freeCompilerArgs += "-opt-in=$optIns"
    }
}

dependencies {
    implementation(Libs.Apollo.api)
    implementation(project(Module.apollo))
    implementation(Libs.Koin.core)
    implementation(Libs.Timber.core)

    testImplementation(project(Module.commonTest))
    testImplementation(TestLibs.JUnit.core)
    testImplementation(TestLibs.Kotest.assertions)
    testImplementation(TestLibs.MockK.core)
    testImplementation(TestLibs.KotlinX.coroutines)
}
