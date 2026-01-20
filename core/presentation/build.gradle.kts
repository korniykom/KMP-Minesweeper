plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("com.korniykom.convention.android.library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm("desktop")
    androidTarget()

    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {

            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.material3)
            implementation(libs.compose.icons.extended)


        }
    }

}