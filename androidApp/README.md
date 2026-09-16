# androidApp

The Android application shell. Thin by design — everything that can live in `shared/` does.

## What belongs here

| | |
|---|---|
| `Application` class | Koin startup, Sentry init, notification channel registration |
| `MainActivity` | Hosts the Compose Multiplatform root from `shared/app-ui` |
| `widget/` | **Glance** widgets — small, medium, large. Read the same Room database the app writes |
| `notification/` | `AlarmManager` receivers, `WorkManager` re-materialiser worker, FCM service |
| `res/values/` | Only what the platform requires — app name, theme, colours for the splash |
| `res/xml/` | `locales_config.xml`, `network_security_config.xml`, App Links `assetlinks` association |
| `AndroidManifest.xml` | Permissions, App Links intent filters, widget receivers |

## What does NOT belong here

Product logic, screens, data models, formatting. If you are writing business logic in this module, it is in the wrong place — move it to a `shared/feature-*` or `shared/core-*` module.

## Manifest rules — these are compliance requirements, not preferences

**Declare:**
- `SCHEDULE_EXACT_ALARM`
- `POST_NOTIFICATIONS`
- `INTERNET`
- `RECEIVE_BOOT_COMPLETED` (to re-materialise reminders after reboot)

**Never declare** — each one is a rejection or suspension risk:
- `USE_EXACT_ALARM` — blocks the upload; we do not qualify
- `READ_MEDIA_IMAGES` / `READ_MEDIA_VIDEO` / `READ_EXTERNAL_STORAGE` — use the Photo Picker and SAF instead
- `READ_CONTACTS` — share-sheet invites only
- `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`
- Any location permission

**Check the *merged* manifest, not this file.** KMP image and media libraries inject media permissions transitively:

```bash
./gradlew :androidApp:processReleaseManifest
# then inspect build/intermediates/merged_manifests/release/AndroidManifest.xml
```

## Widgets

Glance widgets read the Room database directly — the same pattern as Motivoa's `StreakWidget`. Formatting logic comes from `shared/core-designsystem` and `shared/core-datetime`; only the Glance composables are written here.

Interactive "Done" actions log a `CareEvent` without opening the app, then trigger a widget update for every household member via sync.

## Build targets

- `compileSdk` / `targetSdk` **36** (Play requirement)
- `minSdk` **26**
- AAB output with Play App Signing
- 16 KB page-size compliance must be verified on the release artifact — see `docs/COMPLIANCE.md`
