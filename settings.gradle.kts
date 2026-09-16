@file:Suppress("UnstableApiUsage")

// Pet Care Hub — module graph.
// Layering rule: feature-* -> core-* -> core-model. No feature depends on another feature.
// expect/actual lives ONLY in core-* modules, never in feature-*.

rootProject.name = "PetCareHub"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// ---- NOTE ---------------------------------------------------------------
// Every include below is COMMENTED OUT on purpose.
//
// The directories exist so the shape of the project is visible, but no module
// has a build.gradle.kts yet — this repo is structure and documentation only.
// A Gradle sync would fail on the first missing build file, so the includes are
// disabled rather than left to break the IDE.
//
// Uncomment each line as that module gets a real build file in Phase 1.
// See docs/PHASE-0.md.
// -------------------------------------------------------------------------

// ---- Applications -------------------------------------------------------
//include(":androidApp")

// ---- Core: no feature may be depended on from here ----------------------
//include(":shared:core-model")          // pure data classes; depends on nothing
//include(":shared:core-common")         // Result, dispatchers (expect), Clock, UUID (expect)
//include(":shared:core-datetime")       // kotlinx-datetime + expect locale-aware formatting
//include(":shared:core-designsystem")   // palette (accentFill rule), type, motion, atoms
//include(":shared:core-database")       // Room entities/DAOs + expect builder (App Group on iOS)
//include(":shared:core-datastore")      // DataStore Preferences + expect path
//include(":shared:core-network")        // Ktor + Supabase client
//include(":shared:core-sync")           // PowerSync schema, event model, conflict policy
//include(":shared:core-notifications")  // expect Scheduler; rolling-window materialiser
//include(":shared:core-billing")        // RevenueCat wrapper, Entitlement model
//include(":shared:core-ai")             // interface AiEngine + UnsupportedAiEngine (default)
//include(":shared:core-files")          // FileKit wrapper, compression, expect share sheet

// ---- Features -----------------------------------------------------------
//include(":shared:feature-pets")        // profile, identity, archive/memorial
//include(":shared:feature-log")         // the shared household care log (event-sourced)
//include(":shared:feature-reminders")   // schedules, medication, permission health
//include(":shared:feature-household")   // invites, members, roles, sitter mode
//include(":shared:feature-record")      // vaccinations, visits, conditions, labs, weight
//include(":shared:feature-documents")   // vault + viewer
//include(":shared:feature-export")      // shaped exports: boarding, handover, claim, sitter
//include(":shared:feature-paywall")     // one Compose paywall driven by RevenueCat offerings
//include(":shared:app-ui")              // Navigation 3 graph, scaffold, theme host
