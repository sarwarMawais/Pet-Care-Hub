# iosApp

The iOS application shell. Xcode project, Swift, and the three screens that are deliberately native.

> **This cannot be built from Windows.** Requires macOS and Xcode 26+. See `docs/PHASE-0.md`.

## What belongs here

| | |
|---|---|
| `iOSApp.swift` | App entry, Koin startup, Sentry init |
| `ContentView.swift` | Hosts `ComposeUIViewController` from `shared/app-ui` via `UIViewControllerRepresentable` |
| `Auth/` | **Sign in with Apple** — native `ASAuthorizationController`. Mandatory because we offer Google sign-in (Apple 4.8) |
| `Viewer/` | Document preview via `QLPreviewController`, hosted inside Compose with `UIKitViewController` |
| `Widgets/` | **WidgetKit** extension in SwiftUI — small, medium, large, plus App Intents for interactive logging |
| `PrivacyInfo.xcprivacy` | Required. Rejected at upload without it |
| `Info.plist` | Purpose strings, Universal Links association |

## The three native screens, and why

1. **Sign in with Apple** — the native flow is required and materially better than a web fallback.
2. **Document viewer** — `QLPreviewController` handles PDFs, multi-page, zoom and share correctly. Re-implementing it in Compose would be worse and slower.
3. **Long-form text entry** (vet notes, house notes) — CMP's native UIView-backed text input is still experimental; the cross-platform implementation is the weakest part of Compose on iOS.

Everything else — the feed, quick log, pet profiles, the record, reminders, the paywall — is Compose Multiplatform.

## App Group — required for widgets

The widget extension is a separate process with its own sandbox. To share data:

1. Enable an App Group (`group.com.petcarehub`) on **both** the app and the widget extension targets.
2. Put the SQLite file in the App Group container (`containerURL(forSecurityApplicationGroupIdentifier:)`) rather than Documents, so both processes open the same database. `shared/core-database` expects this path from its iOS `actual`.
3. Link the KMP framework into the widget extension so its `TimelineProvider` calls into Kotlin for data and formatting.
4. Call `WidgetCenter.shared.reloadTimelines` from Kotlin after a write.

## Purpose strings — generic strings are an automatic rejection

| Key | Needed? | String |
|---|---|---|
| `NSCameraUsageDescription` | Yes | "…uses the camera so you can take photos of your pets and capture vet invoices or documents to keep with their records." |
| `NSPhotoLibraryUsageDescription` | **No** | Use `PHPickerViewController` — no permission, no string |
| `NSPhotoLibraryAddUsageDescription` | Only for lost-pet posters | "…saves the lost-pet poster you created to your photo library so you can print or share it." |
| `NSFaceIDUsageDescription` | Only if biometric lock ships | "Use Face ID to unlock … and keep your pets' records private." |

**Do not add** location, contacts, microphone, health, calendar or Bluetooth strings. Any unused key invites a reviewer question.

## Privacy manifest

`PrivacyInfo.xcprivacy` must declare `NSPrivacyCollectedDataTypes` matching the App Store Connect Nutrition Labels exactly, plus `NSPrivacyAccessedAPITypes` reason codes:

- `NSPrivacyAccessedAPICategoryUserDefaults` → **CA92.1**
- `NSPrivacyAccessedAPICategoryFileTimestamp` → C617.1 / 0A2A.1
- `NSPrivacyAccessedAPICategoryDiskSpace` → E174.1 / 85F4.1
- `NSPrivacyAccessedAPICategorySystemBootTime` → 35F9.1

A KMP framework does **not** carry a privacy manifest automatically. Verify it survives packaging: `Product → Archive → Generate Privacy Report`, then diff against the declared labels before every submission.

## Notifications

iOS silently keeps only the **64 soonest-firing pending local notifications**. The rolling-window materialiser in `shared/core-notifications` stays under 40. Never schedule one notification per occurrence for a long-running medication course — use `UNCalendarNotificationTrigger` for regular series.
