# Session log

**The durable state of this project.** Agent memory is keyed to the working directory and is not guaranteed to exist — this file is. Anything that matters to the next session goes here, not only in memory.

Two parts: **Where we are right now**, rewritten every session, and the **history** below it, which is append-only.

---

# Where we are right now

> **Last updated:** 2026-09-17 (session 3)
> **Phase:** Phase 0 in progress — Android toolchain works, nothing else built
> **Branch:** `main` · **Remote:** `https://github.com/sarwarMawais/Pet-Care-Hub.git`

## Status

| | |
|---|---|
| Git remote | **Exists now.** `origin` -> `sarwarMawais/Pet-Care-Hub`, `main` tracks `origin/main`. The branch was renamed `master` -> `main` this session to match the stated convention |
| Application code | **Two modules build.** `:shared:core-model` (one type, `Species`, 6 passing tests) and `:androidApp` (one throwaway Compose screen). Nothing else |
| Gradle wrapper | **Committed**, pinned to **9.6.0** |
| Gradle sync | **Works** for those two modules. The other 20 `include()` lines are still commented out |
| Build baseline | Gradle 9.6.0 · AGP 9.4.0 · Kotlin 2.3.20 · KSP 2.3.12 · CMP 1.12.0 · compileSdk 37 / targetSdk 36 · JDK 25. **Three of these differ from the plan** — see [`adr/0006`](adr/0006-agp9-module-plugins.md) |
| APK on a device | **No.** The debug APK has been built, never installed or run. Compiling is not running |
| Documentation | Complete and corrected against what was actually verified today |
| Legal entity | Not started |
| Developer accounts | Not created |
| Mac / iOS toolchain | Not acquired |
| Supabase project | Not created |

## What was actually verified on 2026-09-17

Measured, not reasoned about:

- `./gradlew :androidApp:assembleDebug` -> **BUILD SUCCESSFUL**, `androidApp-debug.apk`, 11.5 MB
- `./gradlew :shared:core-model:testAndroidHostTest` -> **6 tests, 0 failures, 0 skipped**
- Merged debug manifest contains **no `android.permission.*` entry at all** — the only `<uses-permission>` is `com.petcarehub.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`, which androidx defines for itself
- The iOS targets (`iosX64`, `iosArm64`, `iosSimulatorArm64`) **configure and compile to klib on Windows**; only linking is skipped. That is more iOS coverage from this machine than the docs assumed — JVM-only API leaks in `commonMain` will fail the build here, not six months from now on a Mac

## Next up — do these in order

**Yours, not an agent's — start today, it has the longest lead time:**

1. **Apply for a D-U-N-S number.** Free, ~20 minutes, 5–14 business days, and it blocks both Organization enrolments.
2. **Register the legal entity**, business address (not home — publicly displayed under the EU DSA) and business phone.
3. **Trademark search** on "Pet Care Hub" — US / EU / UK / CA. See [`adr/0001`](adr/0001-provisional-name.md).

**Buildable on this Windows machine, in this order:**

4. **Run the APK.** Start an emulator (`android-36` system image is installed) or attach a device, `./gradlew :androidApp:installDebug`, and confirm the screen renders. Until this happens "it builds" is all anyone can claim.
5. **Add `:shared:core-common`** — copy `shared/core-model/build.gradle.kts` verbatim as the template, change the namespace, uncomment its `include()` in `settings.gradle.kts`. **It must have `withHostTest {}`** or its tests will silently not run. Put the `expect` declarations for `Uuid` and `Clock` in it, with a test proving two generated UUIDs differ.
6. **Add `:shared:core-datetime`** on top of it — `kotlinx-datetime` plus the `expect` locale-aware formatter. This is the module that proves the no-`java.time` rule holds, because the iOS klib compile will reject any leak.
7. **Write a merged-manifest check script** (`scripts/check-merged-manifest.*`) that strips XML comments before matching, and wire it into CI. Today's check was done by hand and the naive version produced two false positives.
8. **Re-verify the rest of `libs.versions.toml`.** Only `agp`, `kotlin`, `ksp`, `compileSdk`, `composeMultiplatform`, `activityCompose` and `composeBom` have been confirmed to resolve. Every other pin in that file is still unverified research output, and three of the seven checked were wrong.

**Blocked until a Mac or Codemagic exists:**

9. Decide Mac mini vs cloud macOS, then get a signed iOS hello-world onto a physical device.
10. Spike A (Navigation 3 + swipe-back), Spike B (Room KMP + PowerSync + Supabase EU), Spike C (reminders both platforms).
11. Ask Play whether a pet-medication reminder qualifies for `USE_EXACT_ALARM`. Expected answer: no.

## Blocked on the user — do not decide these alone

- **Final product name** (trademark search pending) -> decides the package and bundle ID
- **Mac mini purchase vs cloud macOS rental** — the plan recommends buying
- **Species scope at launch** — recommendation is dog + cat templates with "Other" fully supported. Note `Species` in `core-model` already encodes this shape; changing the recommendation means changing that type
- **Lifetime price** ($79.99 proposed) and the free-tier document cap (20 or 50)
- **Whether to engage EU legal counsel** for GDPR processor agreements and the EAA exemption question

## Open questions carried forward

- ⚠️ The `USE_EXACT_ALARM` reading is inferred from policy language, not an explicit Google statement.
- ⚠️ Whether **pet** health data counts as "Health" under Apple's Nutrition Labels or Play's Data safety form. No policy text addresses animals.
- ⚠️ EAA microenterprise exemption varies by member state transposition.
- ⚠️ **AGP 9 is unproven here beyond two trivial modules.** Room, KSP and PowerSync integration are all still untested on this baseline, and `com.android.kotlin.multiplatform.library` is young. [`adr/0006`](adr/0006-agp9-module-plugins.md) records the escape hatch if it blocks Phase 1.
- Most of `gradle/libs.versions.toml` remains unverified. Treat every unannotated version as a guess.

## Advice on record, not yet acted on

**Finish and ship Motivoa before starting Phase 1 here.** Remaining work there is billing, the on-device AI red-team probes, and Play submission — weeks, not months. Two half-shipped apps is worse than one shipped app plus a plan. Phase 0 work like today's does not conflict with that; feature code would.

---

# History

Append a new entry at the **top of this section** each session. Never edit an older entry — if something turned out to be wrong, say so in the new entry.

---

## Session 3 — 2026-09-17 · GitHub, and the first build that actually runs

**Commits:** `c048b5c` (the plan narrative, rebased onto the GitHub initial commit), `61ab214` (the first working build)

**Done**

- **Connected the repo to GitHub.** `origin` -> `sarwarMawais/Pet-Care-Hub`. The remote already held a GitHub-generated `Initial commit` with a one-line README, so the local history was **rebased onto it** rather than force-pushed — nothing was destroyed and the push was a fast-forward. The README add/add conflict was resolved in favour of the real one.
- **Renamed `master` -> `main`.** The repo had been on `master` the whole time while every document said `main`.
- Committed the previously untracked `docs/Artifect.md` (the 770-line long-form plan).
- **Built the project for the first time.** Gradle wrapper committed at 9.6.0; `:shared:core-model` and `:androidApp` both build; `:androidApp:assembleDebug` produces an APK.
- Added `Species` to `core-model` with 6 tests pinning product law 7 (free-text fallback, species-scoped tasks never leak across animals). All pass.
- Audited the merged manifest. Clean — no `android.permission.*` entry at all.

**Decisions made**

- **[`adr/0006`](adr/0006-agp9-module-plugins.md) — moved to AGP 9 and adopted its module plugin model as the project template.** Shared modules use `com.android.kotlin.multiplatform.library`; `androidApp` is a plain Android module and not a KMP module. The user chose this over staying on the plan's AGP 8.13.2 with a downgraded Compose Multiplatform 1.11.1. Migrating 2 modules now beats migrating 21 later.
- **Commented out `includeBuild("build-logic")`** in `settings.gradle.kts` rather than building it out. Convention plugins are not worth it at two modules; the line comes back with the first real convention plugin.
- Kept `compileSdk` and `targetSdk` deliberately different (37 / 36) and wrote down why, so a future session does not "fix" the mismatch.

**Corrected**

- **The docs were wrong about why the build fails.** They said the only cause was the commented-out `include()` lines. In fact `settings.gradle.kts` referenced `includeBuild("build-logic")` against a directory holding nothing but an empty `.gitkeep`, which failed *before* Gradle reached the includes. Uncommenting a module would not have helped.
- **`ksp = "2.3.20-2.0.1"` never existed.** KSP stopped mirroring Kotlin's version after `2.2.21-2.0.5`; from `2.3.0` it versions independently. Now `2.3.12`, verified against Maven Central.
- **`agp = "8.13.2"`, `compileSdk = "36"` and `composeMultiplatform = "1.12.0"` were mutually impossible.** Compose Multiplatform 1.12.0 pulls androidx.compose 1.12.0, which requires AGP >= 9.1.0 and compileSdk >= 37.
- **Gradle 9.2 (from `PHASE-0.md`) is too old.** AGP 9.4.0 requires 9.6.0.
- **`kotlin.native.cacheKind=static` was removed in Kotlin 2.3.20.** The research that recommended it predates the release; it now only prints a deprecation warning. Removed from `gradle.properties`.
- **The session log claimed head `daceecb` on branch `main`.** Both were wrong — the branch was `master`, and the head had moved.

**Learned**

- **A shared module missing `withHostTest {}` has no executing tests while reporting `BUILD SUCCESSFUL`.** Under the new KMP library plugin, host unit tests are opt-in. `commonTest` still compiles, so nothing looks wrong. On Windows the iOS test binaries cannot link either, so the module runs *nothing*. Given the convention "anything pure goes in `commonTest`", this could have hollowed out the entire test suite invisibly. Now in `CLAUDE.md`.
- **`allTests` is not the test task.** It reported `NO-SOURCE` and `BUILD SUCCESSFUL` while running zero tests. Always confirm against the JUnit XML in `build/test-results/`, not the console.
- **iOS targets compile to klib on Windows.** Only linking needs a Mac. The docs assumed no iOS verification was possible from this machine; in fact `commonMain` is type-checked against Kotlin/Native here, which catches `java.time`-class leaks immediately. This is a meaningfully better position than the plan assumed.
- **`.properties` files eat backslashes.** `sdk.dir=C:\Users\...` silently mangles because `\U` is an escape, surfacing as `java.io.IOException: Invalid file path`. Use forward slashes. The same bug then bit the Python script that wrote this entry.
- **Grepping a merged manifest gives false positives** — the merger preserves XML comments, so the comment warning against `USE_EXACT_ALARM` matched. Two "violations" were both our own documentation.
- Motivoa had uncommitted changes in `companion/` before this session and was not modified — only read from.

---

## Session 2 — 2026-09-16 · repo, structure and self-containment

**Commits:** `694ca91`, `daceecb`

**Done**

- Created the repository at `C:\Users\MuhammadSarwar\AndroidProjects\Pet`, git initialised, committed to `main`.
- Laid out the KMP module graph as empty source sets: 12 `core-*`, 9 `feature-*`, plus `androidApp` and `iosApp`.
- Wrote `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`, `.gitignore`, and `gradle/libs.versions.toml` with every dependency carrying a version, a risk rating and a provenance note.
- Expanded the plan into a full 972-line `docs/PLAN.md` and mirrored the planning-session research into `docs/research/`.
- Wrote `ARCHITECTURE.md`, `COMPLIANCE.md`, `PHASE-0.md`, five ADRs, and module READMEs for `shared/`, `androidApp/` and `iosApp/`.
- Second pass after review: added `START-HERE.md`, `CONTEXT.md`, `MOTIVOA-REUSE.md`, `GLOSSARY.md`; fixed stale paths in `SUMMARY.md`; verified every relative link resolves.

**Decisions made**

- **Commented out every `include()` in `settings.gradle.kts`.** The directories show the shape, but an uncommented include would fail the Gradle sync on the first missing build file. Uncomment them one at a time in Phase 1.
- **Copy five assets from Motivoa rather than porting it.** The effort table (108 files for resources, 45 for `java.time`, 39 for `Context`, 28 for DI) makes the case.
- Kept the condensed plan as `SUMMARY.md` rather than overwriting it, because it holds the how-we-got-here context.

**Corrected**

- `CompanionChibi.kt` is **1,213 lines**, not the 1,130 the research report stated. All Motivoa paths in `MOTIVOA-REUSE.md` were verified against the working tree rather than trusted from the report.

**Learned**

- The repo could not previously answer "where is Motivoa?" — `CLAUDE.md` referenced its files without giving a path. Fixed. This is the class of gap to watch for: **docs that assume the reader shares the author's context.**

---

## Session 1 — 2026-09-15 · research and plan

**Artifact:** https://claude.ai/artifact/VaMa9VqJyBNvxiUST5zWT8

**Done**

- Explored and rejected three product directions before pets: Trainline-style rail booking, a Vinted-style marketplace, and a pharma B2B SaaS (**declined by the user — do not re-propose**).
- Ran three parallel research passes: store policies, competitors and user research, and the KMP stack plus a Motivoa portability audit. Store figures were scraped live; several hundred verbatim reviews collected.
- Wrote the plan: honest growth ceiling, 12 ranked jobs-to-be-done, competitor scoreboard, 12 product laws, pricing, all 22 screens across 7 facets, architecture, compliance, roadmap.

**Decisions made**

- The thesis: combine shared daily logging with a portable health record. Nobody does both.
- Unlimited pets free forever; per-household pricing; a lifetime tier.
- Supabase EU + PowerSync; append-only care log; reminders as data.
- Sized the business honestly at 100–300K installs and 3–6K paying households, not millions.

**Corrected during the session**

- "Did anyone feed the dog?" is **not** an unserved hook — at least eight apps do it, all tiny. The gap is combining it with records.
- Published pet-app market figures vary 2.3× between research firms; none of them drove the plan.
