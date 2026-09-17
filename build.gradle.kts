// Pet Care Hub — root build file.
// Plugins are declared here with `apply false` and applied per module.
// Structure only: no module currently applies them. See docs/PLAN.md Phase 0.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    // Shared KMP modules use this instead of android.library — AGP 9 rejects the classic
    // Android plugins alongside Kotlin Multiplatform. See docs/adr/0006-agp9-module-plugins.md.
    alias(libs.plugins.android.kmp.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
}
