buildscript {
    repositories {
        gradlePluginPortal()
        google()

        mavenCentral()
    }
    dependencies {
        classpath(Plugins.Android.gradle)
        classpath(Plugins.Kotlin.gradle)
        classpath(Plugins.Gradle.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}
