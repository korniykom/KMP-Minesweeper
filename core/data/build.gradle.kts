import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("com.korniykom.convention.android.application")
}

kotlin {
    androidTarget()
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {

        }
        commonMain.dependencies {

            implementation(libs.koin.core)
            implementation(libs.bundles.koin.compose)

            implementation(libs.datastore.preferences.core)
        }
        desktopMain.dependencies {

        }
    }
}
