buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.0") // compatible con AGP
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21") // Kotlin
    }
}

plugins {
    id("com.android.application") version "8.9.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}