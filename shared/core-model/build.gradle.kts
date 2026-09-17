// core-model — pure data classes. Depends on nothing.
//
// This is the bottom of the layering rule (feature-* -> core-* -> core-model), so it must
// stay dependency-free: no Room, no serialization, no Compose. If something here needs a
// library, it belongs in a different module.
//
// No expect/actual either. This module has to compile identically on every target.
//
// Uses com.android.kotlin.multiplatform.library, not com.android.library: AGP 9 refuses to
// apply the classic Android plugins alongside Kotlin Multiplatform. See docs/adr/0006.
// The androidLibrary {} block below replaces both androidTarget() and the old android {} block.

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.petcarehub.coremodel"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()

        // REQUIRED, and easy to miss. Under com.android.kotlin.multiplatform.library, host
        // (JVM) unit tests are opt-in: without this block commonTest still COMPILES but never
        // RUNS, and Gradle reports success. On Windows the iOS test binaries cannot link
        // either, so omitting this leaves the module with no executing tests at all.
        // Every shared/* module needs it. See docs/adr/0006-agp9-module-plugins.md.
        withHostTest {}
    }

    // Declared now so the target set is honest, even though Kotlin/Native for iOS cannot be
    // linked on Windows. Compiling them here still catches JVM-only APIs (java.time,
    // java.util.UUID and friends) the moment they are introduced, which is the trap
    // CLAUDE.md warns about.
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
