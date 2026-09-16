# shared/

All the Kotlin Multiplatform code. Everything that can live here, does.

## Layering

```
feature-*  ->  core-*  ->  core-model
```

Three rules, in order of importance:

1. **No feature depends on another feature.** Cross-feature coordination goes through `core-model` events.
2. **`expect`/`actual` lives only in `core-*`** — never in a feature module. This is the single discipline that keeps iOS out of product code.
3. **`core-model` depends on nothing.** Pure data classes, no Compose, no coroutines.

## Modules

### core

| Module | Holds | Platform code |
|---|---|---|
| `core-model` | Pure data classes | — |
| `core-common` | `Result`, dispatchers, `Clock`, UUID | `expect val ioDispatcher`, `expect fun randomUuid()` |
| `core-datetime` | kotlinx-datetime helpers | `expect fun bestPattern(locale, skeleton)` — iOS uses `NSDateFormatter.dateFormatFromTemplate`, Android uses `getBestDateTimePattern` |
| `core-designsystem` | Palette (the `accentFill` contrast rule), typography, motion tokens, atoms | — |
| `core-database` | Room entities, DAOs, migrations | `expect` builder; the iOS actual must point at the **App Group container**, not Documents, so the widget can read it |
| `core-datastore` | Preferences | `expect` file path |
| `core-network` | Ktor + Supabase client | `expect` HttpClientEngine — OkHttp / Darwin |
| `core-sync` | PowerSync schema, outbox, conflict policy | — |
| `core-notifications` | Scheduler interface, the rolling-window materialiser | WorkManager + AlarmManager / `UNUserNotificationCenter` |
| `core-billing` | RevenueCat wrapper, `Entitlement` | — (purchases-kmp covers both) |
| `core-ai` | `AiEngine` interface + `UnsupportedAiEngine` **as the default** | ML Kit GenAI / Apple Foundation Models |
| `core-files` | FileKit wrapper, image compression | `expect` share sheet (~40 lines per platform) |

### feature

| Module | Screens |
|---|---|
| `feature-pets` | Pet profile, identity, archive and memorial |
| `feature-log` | Today feed, quick log — the append-only care log |
| `feature-reminders` | Medication schedules, reminder health and permissions |
| `feature-household` | Invites, members, roles, sitter mode |
| `feature-record` | Vaccinations, visits, conditions, labs, weight |
| `feature-documents` | Vault and viewer |
| `feature-export` | Boarding certificate, new-vet handover, claim pack, sitter brief |
| `feature-paywall` | One Compose paywall driven by RevenueCat offerings |
| `app-ui` | Navigation 3 graph, scaffold, theme host |

## Wrapping third-party libraries

Alarmee, PdfKmp, Peekaboo and supabase-kt are each maintained by one or two people. **Every one of them is wrapped behind our own interface** in the owning `core-*` module, so replacing one is a single-file change.

`core-ai` is the pattern to copy — an interface, platform actuals, and a first-class `Unsupported` implementation that is the default rather than an afterthought.

## Testing

- Everything pure goes in `commonTest`: domain logic, schedule computation, import parsers, travel-rule evaluation, state reducers, ViewModels.
- `runComposeUiTest` runs common UI tests on Android, desktop **and iOS** as of CMP 1.11.
- Room migration tests live in `androidInstrumentedTest`; cover the logic in `commonTest`.

## Things that compile on Android and break on iOS

`java.time` · `java.util.UUID` · `java.io.File` · `System.currentTimeMillis()` · `SimpleDateFormat` · `Locale` · `org.json` · `Dispatchers.IO`

All JVM-only. All silently fine until the iOS target is added.
