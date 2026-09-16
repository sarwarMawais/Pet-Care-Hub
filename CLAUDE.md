# Pet Care Hub — working agreements

Read this before touching anything. This file is the short version that must hold on every change.

> **Coming to this project with no prior context? Read [`docs/START-HERE.md`](docs/START-HERE.md) first** — it
> gives the reading order, the current state, and what you are not allowed to change.
> Then [`docs/CONTEXT.md`](docs/CONTEXT.md) for how we got here, and [`docs/PLAN.md`](docs/PLAN.md) for the full plan.

## Session protocol — required, every session

Memory is keyed to the working directory, so opening this folder gives a **fresh, empty memory**.
**The repository carries the state; memory is only a convenience layer.** If a fact exists only in
memory, treat it as lost.

**Start:** read [`docs/SESSION-LOG.md`](docs/SESSION-LOG.md) → "Where we are right now", then
`git log --oneline -10`. If the log and the git history disagree, **trust git and fix the log.**
State what you found and what you plan to do before starting multi-step work.

**End — all five, none optional:**

1. Rewrite the "Where we are right now" block in `docs/SESSION-LOG.md` so it is accurate *now*
2. Add a history entry at the top of that file: date, commits, done, decided, corrected, learned
3. Write **specific** next steps — "uncomment `:shared:core-model`, add its build file, verify the build passes", not "continue with the modules"
4. Update every doc you invalidated, in the same commit; add an ADR for any expensive-to-reverse decision
5. Update memory (short, pointer-shaped) and commit to `main`

Full protocol, the start-of-session prompt, and the definition of done:
**[`docs/AGENT-PROMPT.md`](docs/AGENT-PROMPT.md)**.

## What this is

A household-shared pet care app for Android **and** iOS, Kotlin Multiplatform + Compose Multiplatform.
Daily care log (who fed/walked/medicated, when) fused with a portable lifetime health record,
plus exports shaped for the moment someone demands paperwork.

**Status: planning complete, implementation not started.** The repo holds structure and docs only.

## The twelve product laws

Each one answers a documented failure in a competitor. Breaking one is not a style choice — the
reviews already say what happens. Full evidence in `docs/research/competitors-and-users.md`.

1. **Unlimited pets in the free tier, forever.** Six apps were punished for gating this. Never retract it.
2. **Archiving a pet that has died is free and dignified.** Never consumes a slot, never shows a paywall, never gets a promotional notification again.
3. **Sync is near-real-time and attributed.** Every log line shows who and when. Eventually-consistent-on-restart is broken at the premise.
4. **Reminders are data, not alarms.** The schedule lives in the DB; the OS holds only a rolling window.
5. **Documents open offline, in-app, at the vet counter.** Never "sent to your phone to view".
6. **Exports are shaped for the job** — boarding certificate, new-vet handover, insurance claim pack, sitter brief, travel readiness. Not one generic PDF.
7. **Every dropdown has a free-text fallback.** Rescue mixes, arthropods and horses exist. Species-scoped tasks never leak across animals.
8. **Offline-first for reads and writes.** The 2am emergency vet visit is the moment the record must open.
9. **Never retract a free feature. Never break a lifetime promise.**
10. **Reply to every review and support email within 24 hours.** Measurably a growth channel here, and an Apple 1.2 obligation.
11. **Never appeal to children** in art or copy. Cute is fine; a cartoon mascot with big eyes in a bubbly font drags us into Play's Families policy and COPPA.
12. **Say "record", never "diagnose".** No dose calculators. Banned words: diagnose, treat, prescribe, clinically proven, vet-approved, detects.

## Architecture rules

- **Layering:** `feature-*` → `core-*` → `core-model`. No feature depends on another feature.
- **`expect`/`actual` lives only in `core-*` modules.** Never in a feature. This is what keeps iOS out of product code.
- **Every single-maintainer library is wrapped behind our own interface** (Alarmee, PdfKmp, Peekaboo, supabase-kt) so a swap is one file. `core-ai`'s `AiEngine` is the model to copy.
- **The care log is append-only.** `CareEvent` rows are immutable: client UUID, `occurred_at`, `author_id`, `household_id`. Inserts merge by union, so conflicts are impossible by construction. Edits write a new version row; deletes are tombstones. Last-writer-wins only on the rare same-row edit of a mutable object.
- **Every table is filtered by `household_id` in Postgres RLS**, server-side. A revoked sitter's device must stop receiving data because the server says so, not because the UI hides it.
- **Reminders:** `ScheduleRule` is the truth; occurrences are derived. The materialiser keeps 7 days (Android) / ≤40 slots (iOS, hard cap is 64) and re-runs on foreground, background refresh, reboot, timezone change and after any household member marks a dose done.

## Platform traps that bite

- **Android exact alarms.** Declare `SCHEDULE_EXACT_ALARM` only. Declaring `USE_EXACT_ALARM` blocks the upload — we do not qualify (it is limited to alarm-clock and calendar apps). Check `canScheduleExactAlarms()` before every schedule, listen for the revoked broadcast, and ship an honest inexact fallback.
- **Never request** `READ_MEDIA_*`, `READ_CONTACTS`, `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`, or any location permission. Check the *merged* manifest — KMP image libraries inject media permissions transitively.
- **iOS caps pending local notifications at 64.** Silently. Anything past that is discarded.
- **Compose on iOS draws pixels via Skiko**, so VoiceOver sees only what `semantics {}` declares. Anything custom-drawn is invisible by default. This is both a quality risk and an EU Accessibility Act risk.
- **You cannot build, sign or upload iOS from Windows.** CI ships; only a Mac diagnoses (crash symbolication, VoiceOver, App Store screenshots).
- **`java.time`, `java.util.UUID`, `java.io.File`, `System.currentTimeMillis()`, `SimpleDateFormat`, `Locale`** are all JVM-only. They compile fine on Android and break the moment the iOS target is added.

## Copy in from Motivoa, do not port wholesale

**Motivoa is the same developer's shipped Android app. It lives at
`C:\Users\MuhammadSarwar\AndroidProjects\Motivoa`, source root
`app/src/main/java/com/affirmdaily/motivation/`.**

Five things are worth carrying over. Copy them in; do **not** migrate the app, and do **not** modify
Motivoa from this project — read from it and leave it alone.

**Full file-by-file instructions with verified paths: [`docs/MOTIVOA-REUSE.md`](docs/MOTIVOA-REUSE.md).**
Summary:

| Asset | Where | Why |
|---|---|---|
| Canvas creature rig | `companion/ui/CompanionChibi.kt` + `CompanionCreatures.kt`, `CompanionOutfits.kt`, `CompanionCharacter.kt` | 1,130 lines of pure `DrawScope`, zero assets, zero Android imports, nine species with coats and a face rig. Ports to Compose Multiplatform Canvas unchanged. |
| `CompanionAiEngine` interface | `companion/CompanionAiEngine.kt` | Already models Ready/NeedsDownload/Unsupported with a streaming reply and a first-class "no AI" default. Rename it and the on-device AI architecture is done. |
| `accentFill` palette rule | `ui/theme/MotivoaPalette.kt` + `PaletteContrastTest.kt` | Never fill a surface with `accent`; use `accentDeep` in light mode. 9 of 13 themes fail WCAG AA otherwise. A test pins it. |
| Room migration discipline | `data/local/AppDatabase.kt`, `app/schemas/`, `MigrationTest.kt` | Idempotent `hasColumn()` guards, exported schemas in version control, the `seedMutex` fix for a real double-seed race. For health records, the discipline matters more than the code. |
| Reminder + i18n policy | `notifications/`, `utils/Strings.kt`, `utils/DateFormats.kt` | Distinct notification IDs and PendingIntent request codes; the rule that engines look up string ids and never concatenate sentences. Policy, not code — it survives the port. |

## Naming

The package is `com.petcarehub` and the app name is provisional — **"Pet Care Hub" has not cleared
trademark** and is descriptive. See `docs/adr/0001-provisional-name.md`. Do not print the name into
store metadata, marketing copy or the paywall until the search is done.

## Conventions

- Commit straight to `main`. No feature branches on this repo.
- Kotlin official code style; `ktlint` once the toolchain is up.
- Anything pure goes in `commonTest`. `runComposeUiTest` runs common UI tests on Android, desktop and iOS as of CMP 1.11 — use it. Room migration tests stay in `androidInstrumentedTest`.
- Dates: `kotlinx-datetime` only. Locale-aware formatting goes through `core-datetime`'s `expect`, never a hand-rolled pattern string.
- Strings: Compose Resources. Launch locales are en, de, fr, es. Never concatenate two looked-up strings — that is grammar, and grammar belongs in the resource file.
