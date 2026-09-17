# ADR 0006 — AGP 9, and the module plugin template for every shared module

**Status:** Accepted
**Date:** 2026-09-17

## Context

The plan pinned `agp = "8.13.2"`, `compileSdk = "36"` and `composeMultiplatform = "1.12.0"` from a research pass dated 2026-09-15. The catalogue said explicitly that every version was provisional and had to be re-verified on day one of Phase 0. The first real build did that, and found the three pins are mutually impossible:

1. **Compose Multiplatform 1.12.0 depends on androidx.compose 1.12.0**, which refuses to build on AGP below **9.1.0** and on compileSdk below **37**.
2. **AGP 9.4.0 requires Gradle 9.6.0.** The plan's toolchain note said 9.2.
3. **AGP 9.0 removed support for applying `com.android.library` or `com.android.application` alongside `org.jetbrains.kotlin.multiplatform`.** The error names the replacement: `com.android.kotlin.multiplatform.library`.

The third point is the expensive one. It is not a version bump — it changes the build-file template that all 21 planned modules will be written against, and the shape of `androidApp`. Discovering it after ten modules exist would mean rewriting all of them.

A fallback exists — the Gradle properties `android.builtInKotlin=false` and `android.newDsl=false` — but AGP's own message calls it a way to "temporarily bypass this issue".

The alternative considered was staying on the plan: AGP 8.13.2 with Compose Multiplatform **1.11.1** (which maps to androidx.compose 1.11.x and does build on AGP 8) and compileSdk 36. That path works today and matches the documents, at the cost of starting a greenfield project on the final release of a superseded major version and paying the same migration later across every module instead of across two.

## Decision

**Move to AGP 9 now, and adopt its module plugin model as the project template.**

| | |
|---|---|
| Gradle | **9.6.0** (was 9.5.1; AGP 9.4.0 refuses anything lower) |
| AGP | **9.4.0** (was 8.13.2) |
| Compose Multiplatform | 1.12.0, unchanged |
| `compileSdk` | **37** — forced by androidx.compose 1.12.0 |
| `targetSdk` | **36**, unchanged. This is the value Play requires; compiling against a newer SDK does not opt the app into its runtime behaviour |
| KSP | **2.3.12** (the plan's `2.3.20-2.0.1` never existed — KSP stopped mirroring Kotlin versions after `2.2.21-2.0.5`) |

**Every `shared/*` module** applies `org.jetbrains.kotlin.multiplatform` + **`com.android.kotlin.multiplatform.library`**. The `androidLibrary {}` block inside `kotlin {}` replaces both `androidTarget()` and the old top-level `android {}` block.

**`androidApp` is not a Kotlin Multiplatform module.** It applies `com.android.application` and uses AGP's built-in Kotlin support, with androidx.compose dependencies rather than the Compose Multiplatform artifacts. On Android the two resolve to the same runtime, so the shared UI from `shared/app-ui` will still host inside its Activity unchanged. This module is Android-only by definition — everything cross-platform lives in `shared/`.

**Every shared module must declare `withHostTest {}`** inside its `androidLibrary {}` block.

That last line is the trap worth the ADR. Under the new plugin, host (JVM) unit tests are **opt-in**. Without `withHostTest {}`, a module's `commonTest` source set still **compiles** but never **runs**, and Gradle reports `BUILD SUCCESSFUL`. On Windows the iOS test binaries cannot be linked either, so a shared module missing that one line has *no executing tests at all* while looking green. The project convention is "anything pure goes in `commonTest`", which points the entire test suite straight at this hole.

## Consequences

**Good**

- The project starts on the supported, current toolchain rather than one major version behind on day one.
- The template is settled before it is copied 21 times. Migrating two modules cost one session; migrating twenty would cost several.
- Compose Multiplatform stays at 1.12.0, so nothing in the plan's UI stack has to move backwards.
- `compileSdk` 37 / `targetSdk` 36 is the correct split and is now written down, which stops a future session from "fixing" the mismatch.

**Bad**

- `com.android.kotlin.multiplatform.library` is young. There is far less public troubleshooting material for it than for `com.android.library`, and this project has a first-time KMP developer.
- `androidApp` and `shared/*` now use genuinely different plugin sets and source-set layouts (`src/main` versus `src/androidMain`). That is a real inconsistency to remember, not a tidy uniform rule.
- The plan, `PHASE-0.md` and the version catalogue all had to be corrected in the same change. Anything written before 2026-09-17 that quotes AGP 8.13.2 or Gradle 9.2 is stale.
- AGP 9 has further breaking changes this project has not met yet, because it currently has two trivial modules. Room, KSP and PowerSync integration are all still unproven on this baseline.

## Revisit if

- The new KMP library plugin blocks Room or KSP integration in Phase 1 in a way that costs more than a few days. The escape hatch is `android.builtInKotlin=false` + `android.newDsl=false`, which restores the classic plugins on AGP 9 — treat it as temporary and record the retreat here.
- Compose Multiplatform ships a release that drops the compileSdk 37 floor, in which case the `compileSdk`/`targetSdk` split can be revisited.

## Related

- `gradle/libs.versions.toml` — every version above, with its verification note
- `docs/PHASE-0.md` §2 — toolchain
- `CLAUDE.md` — platform traps
- `shared/core-model/build.gradle.kts` — the reference template for a shared module
- `androidApp/build.gradle.kts` — the reference template for the app module
