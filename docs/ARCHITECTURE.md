# Architecture

The working reference. Rationale and alternatives live in [`PLAN.md`](PLAN.md) §7; the stack survey with versions and risk ratings is in [`research/kmp-stack-and-motivoa-reuse.md`](research/kmp-stack-and-motivoa-reuse.md).

## Shape

Kotlin Multiplatform + Compose Multiplatform. One UI implementation for every screen **except three**, which are deliberately native on iOS:

| Screen | Why native |
|---|---|
| Sign in with Apple | `ASAuthorizationController` — the native flow is required and better |
| Document viewer | `QLPreviewController` beats re-implementing PDF preview |
| Long-form text entry (vet notes, house notes) | CMP's native UIView-backed text input is still experimental |

Widgets are the one deliberate double-write: Glance on Android, WidgetKit/SwiftUI on iOS. The **data and formatting logic are shared**; only the views are not.

## Layering

```
feature-*  ->  core-*  ->  core-model
```

Three rules, in order of importance:

1. **No feature depends on another feature.** Cross-feature coordination goes through `core-model` events.
2. **`expect`/`actual` lives only in `core-*`.** Never in a feature module. This is the single discipline that keeps iOS out of product code.
3. **`core-model` depends on nothing.** Pure Kotlin data classes, no Compose, no coroutines, no serialization annotations that drag in a runtime.

Every single-maintainer library is wrapped behind our own interface so replacing it is one file. `core-ai`'s `AiEngine` is the pattern to copy: an interface, platform actuals, and a first-class `Unsupported` implementation that is the default.

## Data model — the append-only care log

The central design decision. A pet care log is **append-mostly**, so we model it that way and conflicts become impossible rather than merely handled.

```
CareEvent
  id            UUID generated on the client
  household_id  scope for sync and RLS
  pet_id
  author_id     who logged it — shown on every row in the UI
  kind          FED | WALKED | DOSE | PEE | POO | WEIGHT | NOTE | custom
  occurred_at   the real time of the event, not the time it was typed
  created_at    server-assigned
  payload       typed per kind (amount, unit, note, media ref)
  supersedes    nullable — an edit writes a NEW row pointing at the old one
  deleted_at    nullable — a tombstone, never a hard delete
```

Consequences:

- **Inserts merge by union.** Two phones logging offline cannot conflict.
- **Edits are new rows.** The history is preserved, which a health record wants anyway.
- **Deletes are tombstones**, so a delete that syncs late cannot resurrect a row.
- Last-writer-wins applies **only** to mutable, low-contention objects: `Pet`, `ScheduleRule`, `Household`. Those carry `updated_at` and are rarely edited concurrently.

### Sync

- **PowerSync** between local SQLite (the same file Room manages) and Supabase Postgres.
- Partial sync: `WHERE household_id IN (my memberships)`.
- **Postgres RLS enforces the same predicate server-side.** A revoked sitter's device stops receiving data because the server says so, not because the UI hides it. Never rely on client-side filtering for access control.
- Photos and documents go to Supabase Storage in a private bucket under `household_id/`, served by signed URL, cached on device after first fetch.

**Target: a partner's log appears on your phone in under 3 seconds on Wi-Fi.** Offline writes queue and reconcile with no user action.

## Reminders — schedule as data, OS as cache

The second design that decides whether the product works. Every competitor gets this wrong.

```
ScheduleRule
  pet_id, item (medication | preventative | task)
  times[]      explicit clock times, not intervals
  pattern      DAILY | EVERY_N_DAYS | WEEKDAYS | MONTHLY_ON_DATE
  window       start / end, or ongoing
  paused       courses pause and resume without losing history
  critical     may ring through quiet hours
```

- `ScheduleRule` is the **truth**. Occurrences are *derived*, never stored as OS alarms.
- A **materialiser** keeps a rolling window of OS notifications and re-runs on: app foreground, background refresh, device reboot, timezone change, and **after any household member marks a dose done**.
  - Android: 7 days ahead
  - iOS: ≤ 40 slots (the OS silently caps pending local notifications at **64**)
- Android uses `SCHEDULE_EXACT_ALARM` when granted; an inexact fallback plus a visible in-app banner when not. `WorkManager` drives re-materialisation.
- iOS uses `UNCalendarNotificationTrigger` for regular series (one trigger, does not consume the budget per occurrence) and a rolling window for irregular ones.
- **Push is never the primary medication reminder.** FCM/APNs carry "your partner logged a dose" and act as a backstop for long-horizon items like annual boosters.
- A **self-check** compares what should be queued against what the OS reports as queued, and tells the user when they differ. Port Motivoa's `ReminderHealthProbe`.

## Modules

| Module | Holds | Platform code |
|---|---|---|
| `core-model` | Pure data classes | — |
| `core-common` | `Result`, dispatchers, `Clock`, UUID | `expect val ioDispatcher`, `expect fun randomUuid()` |
| `core-datetime` | kotlinx-datetime helpers | `expect fun bestPattern(locale, skeleton)` |
| `core-designsystem` | Palette (`accentFill` rule), type, motion, atoms | — |
| `core-database` | Room entities, DAOs, migrations | `expect` builder — App Group container path on iOS |
| `core-datastore` | Preferences | `expect` file path |
| `core-network` | Ktor + Supabase client | `expect` HttpClientEngine (OkHttp / Darwin) |
| `core-sync` | PowerSync schema, outbox, conflict policy | — |
| `core-notifications` | Scheduler interface, materialiser | WorkManager + AlarmManager / `UNUserNotificationCenter` |
| `core-billing` | RevenueCat wrapper, `Entitlement` | — (purchases-kmp handles both) |
| `core-ai` | `AiEngine` + `UnsupportedAiEngine` (default) | ML Kit GenAI / Apple Foundation Models |
| `core-files` | FileKit wrapper, compression | `expect` share sheet |
| `feature-*` | Product code. **No `expect`/`actual`.** | — |
| `app-ui` | Navigation 3 graph, scaffold, theme host | — |

## Testing

- Everything pure goes in `commonTest`: domain logic, schedule computation, import parsers, travel-rule evaluation, state reducers, ViewModels (with `kotlinx-coroutines-test`).
- `runComposeUiTest` runs common UI tests on Android, desktop **and iOS** as of CMP 1.11. Note 1.11 switched the default dispatcher from `UnconfinedTestDispatcher` to `StandardTestDispatcher` — coroutines no longer auto-advance.
- Room migration tests stay in `androidInstrumentedTest` (`MigrationTestHelper` is Android-side). Cover the *logic* in common.
- Maestro for end-to-end on both platforms.

## Build and release

- **iOS cannot be built, signed or uploaded from Windows.** Codemagic for CI; a Mac for diagnosis (crash symbolication, Instruments, VoiceOver, App Store screenshots).
- Pin the Xcode version in CI — a mismatched SDK breaks framework linking.
- Keep the shared module lean: every transitive dependency is paid for on every iOS link. Release linking is ~10× debug, so only CI needs it; build one architecture locally.
- `kotlin.incremental.native` and Kotlin/Native caching on from day one (already set in `gradle.properties`).

## Things that will break if you forget them

- `Dispatchers.IO` does not exist on Kotlin/Native.
- `java.time`, `java.util.UUID`, `java.io.File`, `System.currentTimeMillis()`, `SimpleDateFormat`, `Locale`, `org.json` — all JVM-only, all compile fine on Android, all fail when the iOS target is added.
- iOS interactive edge-swipe-back is **not free**; it must be wired explicitly.
- Compose on iOS draws via Skiko, so VoiceOver sees only what `semantics {}` declares. Custom-drawn UI is invisible by default.
- Navigation 3 needs hand-written polymorphic `kotlinx.serialization` registration for `NavKey` types off-JVM — reflection-based route serialization does not work on Native.
- 16 KB page-size compliance is about native `.so` files pulled in transitively. Audit the merged output, not your own code.
