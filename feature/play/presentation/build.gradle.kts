plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("com.korniykom.convention.android.library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    jvm("desktop")
    androidTarget()

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

        }
        commonMain.dependencies {

            implementation(libs.koin.core)
            implementation(libs.bundles.koin.compose)

            implementation(projects.core.presentation)
            implementation(projects.core.data)
            implementation(projects.feature.settings.domain)

            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.material3)
            implementation(libs.compose.icons.extended)
            implementation(libs.compose.navigation)


        }
    }

}

