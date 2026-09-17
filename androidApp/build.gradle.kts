// androidApp — the Android shell. Thin by design; see androidApp/README.md.
//
// NOT a Kotlin Multiplatform module. AGP 9 does not allow com.android.application alongside
// the Kotlin Multiplatform plugin, and this module is Android-only by definition anyway:
// everything cross-platform lives in shared/. Kotlin compilation comes from AGP's built-in
// Kotlin support. See docs/adr/0006-agp9-module-plugins.md.
//
// Compose here is androidx.compose, not the Compose Multiplatform artifacts. On Android they
// are the same runtime — CMP resolves to androidx.compose — so the shared UI from
// shared/app-ui will host inside this Activity without translation.

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.petcarehub.android"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.petcarehub"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.shared.coreModel)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)
}
