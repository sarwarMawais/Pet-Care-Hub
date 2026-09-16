Research and audit complete. Here are both reports.

---

# JOB 1 — KMP / CMP TECH STACK, MID-2026

> Version caveat: a few numbers below come from secondary/aggregator sources rather than a changelog I could read directly (flagged inline as *verify*). Pin every version against `gradle/libs.versions.toml` on day one and re-check before the first release branch.

## 1. Compose Multiplatform on iOS

**Status: Stable.** iOS went Stable in CMP **1.8.0** (May 2025). Latest stable is **1.12.0**; the major 2026 releases were **1.10.0** (Jan 2026 — unified `@Preview`, Navigation 3 on non-Android targets, stable Compose Hot Reload) and **1.11.0** (May 2026 — experimental UIView-backed native text input, concurrent rendering on by default). KMP core itself lists Android/iOS/desktop/JVM/JS as Stable; watchOS/tvOS/Wasm are **Beta**.

Known limitations vs SwiftUI:

| Area | 2026 state |
|---|---|
| Text input | Historically the weakest spot. 1.11's native UIView-backed implementation gives real caret movement, native selection handles and the system context menu — but it is **experimental**; the stable cross-platform implementation is still the default. 1.12 added iOS-style selection handles + "Select" in the context menu. |
| Accessibility | Semantics map to native iOS accessibility objects; VoiceOver/AssistiveTouch/Full Keyboard Access work. But it is a *manual mapping* — Skiko draws pixels, so anything not built from Material widgets needs explicit `semantics {}` or VoiceOver sees nothing. A long-standing issue requires `AccessibilitySyncOptions.Always` for reliable VoiceOver, which can cause verbose announcements. **This is your #1 iOS-quality risk for a health-records app.** |
| Scrolling feel | Much improved (concurrent rendering, dedicated render thread). Still not pixel-identical to UIScrollView deceleration/rubber-banding; acceptable for most apps, noticeable to picky reviewers. |
| Keyboard handling | IME insets work but need care; historically the most-filed category after text input. |
| Navigation transitions / swipe-back | The single biggest gotcha. iOS interactive edge-swipe-back is *not* free — Decompose handles it via `predictiveBackAnimation`; Nav3/Navigation-Compose need platform bridging. Prototype this in week 1. |
| Performance | Concurrent rendering default since 1.11; first-frame/startup is heavier than SwiftUI because the Kotlin/Native runtime + Skia must initialise. |

**Navigation recommendation: androidx Navigation 3 multiplatform (`org.jetbrains.androidx.navigation3:navigation3-ui`, v1.1.1), requires CMP ≥ 1.10.** It's Google's forward direction, it's stable, it works on iOS, and the user-owned `SnapshotStateList` back stack is a better fit than Nav2's opaque stack. Gotcha: on iOS/web you **must** hand-write polymorphic `kotlinx.serialization` registration for your `NavKey` types — reflection-based route serialization does not work off-JVM. Maturity risk: **medium** (new paradigm, thin ecosystem). If you want the lowest-risk path and don't mind a non-Google library, **Decompose** is the battle-tested choice with the best iOS back-gesture and process-death story (risk: low). Voyager is in maintenance-ish mode relative to the other two — skip for a new 2026 app.

**Should some screens be SwiftUI?** Yes, three candidates: (a) **any screen dominated by long-form text entry** (pet notes, vet visit write-ups) until native text input graduates from experimental; (b) **Sign in with Apple** — use the native `ASAuthorizationController` flow; (c) **document/photo viewers** — `QLPreviewController`/`PHPickerViewController` beat re-implementing. Everything else (the shared log feed, pet profiles, reminders, paywall) is fine in Compose.

**Interop mechanics:** Kotlin exposes `ComposeUIViewController { YourScreen() }` which returns a `UIViewController`; Swift wraps it in a `UIViewControllerRepresentable` to drop a Compose screen into a SwiftUI hierarchy. The reverse — native inside Compose — uses `UIKitViewController`/`UIKitView` composables to host a UIKit/SwiftUI-hosted controller in a Compose tree. Both directions are supported and documented; state sharing across the boundary is the fiddly part (keep state in Kotlin, pass callbacks).

Sources: [CMP 1.11.0](https://blog.jetbrains.com/kotlin/2026/05/compose-multiplatform-1-11-0/) · [CMP 1.10.0](https://blog.jetbrains.com/kotlin/2026/01/compose-multiplatform-1-10-0/) · [iOS Stable announcement](https://blog.jetbrains.com/kotlin/2025/05/compose-multiplatform-1-8-0-released-compose-multiplatform-for-ios-is-stable-and-production-ready/) · [Supported platforms & stability](https://kotlinlang.org/docs/multiplatform/supported-platforms.html) · [Compatibility & versioning](https://kotlinlang.org/docs/multiplatform/compose-compatibility-and-versioning.html) · [Navigation 3 in CMP](https://kotlinlang.org/docs/multiplatform/compose-navigation-3.html) · [iOS accessibility docs](https://kotlinlang.org/docs/multiplatform/compose-ios-accessibility.html) · [VoiceOver issue #4401](https://github.com/JetBrains/compose-multiplatform/issues/4401) · [Touchlab on iOS interop](https://touchlab.co/jetpack-compose-ios-interop) · [CMP releases](https://github.com/JetBrains/compose-multiplatform/releases)

## 2. Data layer

**Room KMP is production-ready.** `androidx.room` **2.8.x** (2.8.4 current) supports iOS targets in the *stable* namespace via `@ConstructedBy` + `RoomDatabaseConstructor`, paired with `androidx.sqlite` bundled driver. **Room 3.0** (March 2026) is the KMP-first major — adds JS/Wasm, moves to the **`androidx.room3`** namespace (breaking).

**Recommendation: Room 2.8.x** for this app, risk **low**.
- Your developer already has 12 Room migrations and exported schemas in Motivoa — zero learning curve, and `MigrationTestHelper` carries over.
- Room's migration system is meaningfully safer than SQLDelight's raw `.sqm` files for a health-records app.
- Stay on 2.8.x rather than jumping to `androidx.room3` until you've shipped once; the namespace move is pure churn on day one.
- SQLDelight remains the better pick only if you want SQL-first authoring or need Wasm today. It has been KMP-ready longer and is lower-risk on paper — but it's the wrong trade for an Android-native dev.

**DataStore KMP: yes.** `androidx.datastore:datastore-preferences` supports KMP from **1.1.0+** (1.1.7 / 1.2.x line). Risk **low**. Note: you need an `expect/actual` for the file *path* (Android `filesDir`, iOS `NSDocumentDirectory`) — that's the only platform code.

Sources: [Room 3.0 announcement](https://android-developers.googleblog.com/2026/03/room-30-modernizing-room.html) · [Room for KMP setup](https://developer.android.com/kotlin/multiplatform/room) · [DataStore for KMP](https://developer.android.com/kotlin/multiplatform/datastore) · [Room releases](https://developer.android.com/jetpack/androidx/releases/room) · [Room vs SQLDelight 2026](https://docs.bswen.com/blog/2026-03-14-room-vs-sqldelight-kmp/)

## 3. Sync backend — the biggest decision

**Firebase via KMP.** No official Google KMP SDK. Two community paths:
- **GitLive `firebase-kotlin-sdk` v2.7.0** — mature-ish, actively maintained (1.7k stars, 1272 commits), covers iOS/Android/JVM/JS. **But look at the API-coverage numbers it publishes itself: Firestore 23%, Analytics 16%, Cloud Messaging 5%.** For a Firestore-heavy sync app that is a real ceiling — you'll be writing `expect/actual` around native SDKs for anything off the happy path. Risk: **medium-high**.
- **KFire** (2026, beta) — newer, 8 services, auto-init. Beta. Risk **high** for a shipping app.

**Supabase (`supabase-kt`, supabase-community).** Genuinely multiplatform, BOM-managed, modules: `auth-kt`, `postgrest-kt`, `realtime-kt`, `storage-kt`, `functions-kt`, plus `compose-auth`/`compose-auth-ui` and Coil3 integration. Auth covers Google + Apple native sign-in via the compose-auth helpers. Postgres RLS is the right shape for household sharing (`household_id` policy). Risk: **low-medium** (community-maintained but it *is* the officially-listed Kotlin client in Supabase's own docs).

**PowerSync.** Kotlin SDK v1.13.x, supports Android/JVM/Apple (incl. watchOS) via KMP. Sits *between* a local SQLite DB and Postgres/MongoDB, and now (experimentally) Convex. It gives you real offline-first with partial sync and a defined write path. **Ditto** is p2p/BLE-first with a NoSQL document model — over-engineered for household sync over the internet, and its relational story is weak. **Convex** has no first-class KMP SDK; you'd reach it through PowerSync.

**Recommendation: Supabase (EU region) + PowerSync as the sync layer, with Room/SQLite local.** Reasoning against your four criteria:

- *Offline-first + conflict handling for a shared household log*: a pet-care log is append-mostly ("fed at 8am", "gave 5mg at noon"). Model it as **immutable event rows with client-generated UUIDs + `created_at` + `author_id`**, never mutable counters. That makes the merge trivially conflict-free — last-writer-wins on the rare *edit*, union on inserts. PowerSync gives you the queue, retry and partial-sync (`WHERE household_id = ?`) for free. Supabase Realtime alone is fine if you accept a hand-rolled outbox; PowerSync is the ~$50/mo insurance policy against writing that yourself.
- *Cost at 10k–100k users*: Supabase Free covers 50k MAU / 500MB DB / 1GB storage; **Pro is $25/mo + usage, 8GB DB, 100k MAU, 100GB storage**. Photos are your cost driver, not rows — budget storage + egress separately and compress client-side. Firebase's Firestore per-read pricing is the worse shape for a realtime-listener app with chatty household updates.
- *EU data residency / GDPR*: **this is where Supabase wins decisively.** You pick an EU region per project, and because it's open source you retain a self-host escape hatch. Firebase's `eur3` multi-region (europe-west1 + europe-west4, witness in europe-north1) covers Firestore, but **Auth, FCM and Cloud Functions do not all offer equivalent EU residency** — tokens and push payloads may transit US infrastructure, which is an awkward paragraph in a DPA for a health-adjacent app. Caveat both ways: Supabase is a US company and thus CLOUD-Act-exposed despite EU hosting.
- *Sign in with Apple + Google*: Supabase Auth supports both as first-class providers; `compose-auth` handles the native flows. Use **native** Apple sign-in on iOS (App Store requires it if you offer any other social login).

Risk: **medium**. The composite (Supabase + PowerSync + Room) is more moving parts than Firebase, but every part is KMP-native rather than a wrapper.

Sources: [supabase-kt](https://github.com/supabase-community/supabase-kt) · [Supabase Kotlin reference](https://supabase.com/docs/reference/kotlin/introduction) · [PowerSync Kotlin SDK](https://docs.powersync.com/client-sdks/reference/kotlin) · [PowerSync + KMP](https://powersync.com/blog/build-local-first-kotlin-multiplatform-apps-with-powersync) · [PowerSync + Convex](https://powersync.com/blog/convex-powersync-design-notes) · [Choosing an offline-first sync layer for KMP](https://medium.com/@strv/choosing-an-offline-first-sync-layer-for-kmp-884826886e9c) · [GitLive firebase-kotlin-sdk](https://github.com/GitLiveApp/firebase-kotlin-sdk) · [Firestore locations](https://firebase.google.com/docs/firestore/locations) · [Supabase pricing 2026](https://makerkit.dev/blog/saas/supabase-pricing)

## 4. In-app purchases

**RevenueCat `purchases-kmp`.** Android 6.0+ / iOS 13+ / **watchOS 7+**. Wraps Play Billing + StoreKit behind store-agnostic Offerings / Packages / Entitlements / CustomerInfo. **From 3.0.0 the native iOS RevenueCat SDK is bundled into the KMP framework — no CocoaPods, no `kotlin-cocoapods` plugin.** That removes the single most painful part of iOS KMP setup. Requires Kotlin 2.3.20+.

**Two real caveats:** (1) **Paywalls and Customer Center (the `-ui` artifact) are iOS-only** — on Android you build the paywall yourself in Compose, or build one shared Compose paywall and drive it from Offerings metadata (RevenueCat's own `cat-paywall-kmp` sample does exactly this). (2) StoreKit 2 is used by default for *some* operations but not yet for the purchase itself as of the v4 line — *verify current*.

**Adapty KMP** is the credible alternative: native (non-WebView) paywall rendering claimed ~1.6× faster, offline-cached paywalls, unlimited A/B tests. **Qonversion** — not recommended, thinner KMP story.

**Recommendation: RevenueCat.** Risk **low**. Free until $2.5k/mo tracked revenue, largest install base, and the watchOS target is a freebie if you do Apple Watch. Build one shared Compose paywall driven by Offerings rather than using the iOS-only paywall UI — that keeps the two platforms identical and sidesteps the artifact gap.

Sources: [purchases-kmp](https://github.com/RevenueCat/purchases-kmp) · [RevenueCat KMP docs](https://www.revenuecat.com/docs/getting-started/installation/kotlin-multiplatform) · [How we built it](https://www.revenuecat.com/blog/engineering/how-we-built-the-revenuecat-sdk-for-kotlin-multiplatform) · [cat-paywall-kmp](https://github.com/RevenueCat/cat-paywall-kmp) · [Adapty KMP](https://adapty.io/sdk/kmp/)

## 5. Push + local scheduled reminders

Two libraries:
- **Alarmee** (Tweener) — one-off **and repeating** alarms, local notifications with images, plus push via FCM (Android) / APNs (iOS). This is the closer fit for medication reminders because of the repeating API.
- **KMPNotifier** (mirzemehdi) — FCM push + local notifications across Android/iOS/desktop/web. Uses exact alarms with automatic fallback to inexact when denied. **No repeating-notification API** — you schedule each occurrence.

**Android exact-alarm reality (this is the hard part).** From Android 14, `SCHEDULE_EXACT_ALARM` is **denied by default** for apps targeting API 33+ — the user must manually grant "Alarms & reminders." The alternative, `USE_EXACT_ALARM`, is granted at install but is a **Play-reviewed restricted permission limited to alarm-clock/timer and calendar apps**; a pet-medication reminder app will likely *not* qualify, and a failed review blocks publishing. Assume you get `SCHEDULE_EXACT_ALARM` with an in-app rationale + deep link to the settings screen, and degrade gracefully to inexact when denied.

**iOS reality.** Hard OS cap of **64 pending local notifications** per app. With a daily-repeating med reminder you get ~2 months of a single series, or you burn the budget instantly with multiple pets × multiple meds.

**Reliable medication reminders across both — the pattern:**
1. Keep the **schedule as data** in the shared DB (rule rows: pet, med, times, days), never as a pile of OS alarms.
2. Materialise a **rolling window** of OS notifications — e.g. the next 7–14 days — and re-materialise on every app foreground and on a background refresh. This keeps you well under iOS's 64 and re-anchors after reboot/timezone change.
3. Android: `WorkManager` for the re-materialiser + `AlarmManager.setExactAndAllowWhileIdle` for the individual fires when permission is granted; inexact fallback otherwise, with a visible in-app banner explaining reduced precision.
4. iOS: `UNCalendarNotificationTrigger` for repeating series (one trigger, doesn't consume the budget per-occurrence) where the schedule is simple; `UNTimeIntervalNotificationTrigger` for irregular ones.
5. **Never rely on push (FCM/APNs) for a medication reminder.** Push is for "your partner logged a dose" — local scheduling is for the medication itself.

**Recommendation: Alarmee for local + KMPNotifier or raw FCM/APNs for push.** Risk **medium** — both libraries are single-maintainer, and you will likely still write `expect/actual` for the exact-alarm permission dance. Budget real time here; this is the feature most likely to generate 1-star reviews.

Sources: [Alarmee](https://github.com/Tweener/alarmee) · [KMPNotifier](https://github.com/mirzemehdi/KMPNotifier) · [Exact alarms denied by default](https://developer.android.com/about/versions/14/changes/schedule-exact-alarms) · [Schedule alarms guide](https://developer.android.com/develop/background-work/services/alarms)

## 6. Images, files, PDF, sharing

| Need | Pick | Risk |
|---|---|---|
| File/photo/document picking, saving, compression | **FileKit** (vinceglb) — iOS, Android, JVM, Wasm, JS, macOS, **watchOS**, Linux, Windows; native pickers; kotlinx-io + Coil integration. | low |
| Camera capture / in-app crop | **Peekaboo** (onseok) — Android + iOS only, gallery + camera + crop. | medium (narrower maintenance) |
| Image loading | **Coil 3** — multiplatform, the default choice. | low |
| PDF generation | **PdfKmp** (ConaMobileDev) — DSL/layout engine in `commonMain`, renders via `android.graphics.pdf.PdfDocument` on Android and `UIGraphicsBeginPDFContextToData` + Core Graphics on iOS. Compose-style `pdf { page { column { … } } }` DSL. | medium (young) |
| Share sheet | No single great library — `expect/actual` around `Intent.ACTION_SEND` and `UIActivityViewController`. ~40 lines. | low |

**Recommendation: FileKit + Coil 3 + PdfKmp, hand-rolled share sheet.** Prefer FileKit over Calf (which is broader/thinner). If PdfKmp turns out too immature, fall back to `expect fun renderReport(report: Report): ByteArray` with two actuals — Motivoa already proves the Android half works (see Job 2).

Sources: [FileKit](https://github.com/vinceglb/FileKit) · [Peekaboo](https://github.com/onseok/peekaboo) · [PdfKmp](https://github.com/conamobiledev/PdfKmp) · [PdfKmp 1.0 writeup](https://dev.to/conamobile/pdfkmp-10-a-kotlin-multiplatform-pdf-library-for-android-and-ios-2pn)

## 7. Widgets & wearables

There is **no shared widget UI**. Widgets are the one place you write the view layer twice:
- **Android: Glance** (`androidx.glance` 1.2.x) — Compose-ish DSL, Android-only.
- **iOS: WidgetKit** — must be SwiftUI, in a separate app extension target.

**How data gets there.** The extension is a *separate process with its own sandbox*, so the standard pattern is:
1. Enable an **App Group** (`group.com.you.app`) on both the app and the widget extension.
2. Put the SQLite file in the App Group container (`containerURL(forSecurityApplicationGroupIdentifier:)`) instead of Documents — then both processes open the same DB.
3. Export the KMP shared module as a framework the widget extension also links, so the widget's `TimelineProvider` calls into Kotlin for its data. That way the *data* and *formatting logic* are shared even though the views aren't.
4. Call `WidgetCenter.shared.reloadTimelines` from Kotlin after a write.

On Android, Glance widgets just read the same Room DB directly — which is exactly what Motivoa's `StreakWidget` already does.

**Wearables:** watchOS is **Beta** as a Kotlin/Native target — usable for shared business logic linked into a SwiftUI watch app (and RevenueCat explicitly supports watchOS 7+), but Compose Multiplatform does **not** render on watchOS; the watch UI is SwiftUI. Wear OS uses **Wear Compose**, a different artifact from CMP — Android-only, shares your Kotlin logic but not your screens. **Recommendation: defer both to v2.** Risk **high** for launch scope.

Sources: [Supported platforms (watchOS Beta)](https://kotlinlang.org/docs/multiplatform/supported-platforms.html) · [purchases-kmp watchOS support](https://github.com/RevenueCat/purchases-kmp)

## 8. On-device AI

- **Android — Gemini Nano** via ML Kit GenAI / AICore. Broadened beyond Pixel flagships with Android 16 into a slice of the mid-range, but still a **minority of installed devices**, and AICore enforces a per-app quota. The model is a separate system component that can be updated or removed underneath you.
- **iOS — Apple Foundation Models framework (iOS 26).** ~3B parameter on-device model, free, offline, no API key, structured output via `@Generable`. **Per-device availability** — you must check `SystemLanguageModel.default.availability`; older iPhones, managed devices, and users who disabled Apple Intelligence all return unavailable. Not a general world-knowledge model; no meaningful long context. (WWDC 2026 opened the framework to third-party LLM providers — *verify what this means for your use case*.)
- Neither is reachable from `commonMain`. You need `expect interface OnDeviceAi` with a Kotlin actual (ML Kit) and a Swift-bridged actual (FoundationModels via cinterop or a Swift shim).

**Is on-device-first realistic across both in 2026? Partially — and only if "no AI" is a first-class path.** The honest design is: an `interface AiEngine` with a `Unsupported` implementation that is the *default*, a rules-based feature that works for 100% of users, and on-device generation as an enhancement for the minority that have it. Cloud fallback (Gemini API / Claude API) is viable but changes your privacy posture and your Data Safety / App Privacy declarations — for pet *health* records that's a meaningful cost.

**Recommendation:** ship v1 with a rules-based feature behind an engine interface; add ML Kit GenAI + Foundation Models actuals in v1.1. Risk **high** if treated as a headline feature, **low** if treated as progressive enhancement. (Your developer has already built exactly this shape in Motivoa — see `CompanionAiEngine`, Job 2.)

Sources: [Foundation Models in iOS 26](https://www.createwithswift.com/exploring-the-foundation-models-framework/) · [Developer's guide to Foundation Models](https://hackernoon.com/a-developers-guide-to-apples-foundation-models-framework-in-ios-26) · [WWDC 2026 Foundation Models opened to any provider](https://dev.to/arshtechpro/wwdc-2026-apple-just-opened-the-foundation-models-framework-to-any-llm-provider-5ejn)

## 9. Dependency injection

| | Model | KMP fit | Risk |
|---|---|---|---|
| **Koin 4.1.x** (+ koin-annotations 2.3.x, new **Koin Compiler Plugin** replacing `koin-ksp-compiler`) | Runtime service locator; annotations generate DSL at compile time, so no reflection at startup | Best-in-class; the Compiler Plugin needs no per-platform KSP config | **low** |
| **kotlin-inject (+ anvil)** | True compile-time DI, KSP codegen | Good; measurably faster resolution than Koin | medium |
| **Metro** (Zac Sweers) | Compile-time, Dagger-family semantics, KMP-native | Excellent design, newer ecosystem | medium |

**Recommendation: Koin 4.1 with annotations.** Risk **low**. For a solo developer coming from Hilt, Koin's `@Single`/`@Factory`/`@Module` annotations are the shortest bridge — the mental model is close enough to Hilt that the migration is mechanical, and the KMP setup is genuinely one plugin. Metro is the "right" answer architecturally and what I'd pick for a team of five; for a solo dev shipping in 2026 it's a bet on an ecosystem that isn't there yet.

Sources: [Koin KMP setup](https://insert-koin.io/docs/reference/koin-core/kmp-setup/) · [Koin annotations for KMP](https://insert-koin.io/docs/reference/koin-annotations/kmp/) · [koin-annotations releases](https://github.com/InsertKoinIO/koin-annotations/releases) · [Zac Sweers on DI vs service locators](https://www.zacsweers.dev/re-dependency-injection-vs-service-locators/)

## 10. Build / CI / release — the Windows problem

**You cannot build, sign, or upload an iOS app from Windows.** No exceptions — `linkDebugFrameworkIosArm64` requires the Xcode toolchain, and `.ipa` packaging + notarisation require macOS. Options:

| Option | Cost (2026) | Verdict for a solo Windows dev |
|---|---|---|
| **Codemagic** | 500 free min/mo; then **$0.10/min** Mac M2. Annual M2 plan ~$3,990/yr | **Best fit.** Purpose-built for mobile, handles code signing/App Store Connect upload, has first-class KMP support, and you never touch Xcode. |
| **Xcode Cloud** | **25 compute hours/mo included** with the $99 Apple Developer Program; then $49.99/100h, $99.99/250h | Cheapest at low volume. Works with KMP but needs a `ci_post_clone.sh` to install the JDK + run Gradle. Apple-only, no Android builds. |
| **GitHub Actions macOS runners** | ~**$0.062/min**; ~$1.24 per 20-min iOS run. Free/unlimited on public repos | Fine, but you own the Fastlane + match/code-signing setup yourself. Cheapest paid per-minute of the three. |
| **MacStadium / MacinCloud** | ~$60–120/mo dedicated Mac mini | Only worth it if you need interactive Xcode (Instruments profiling, simulator debugging, App Store screenshots). |

**Honest recommendation: buy a used Mac mini (M2/M4, ~€500–700) OR budget one rented Mac.** You will eventually need interactive Xcode for: profiling Compose-on-iOS performance, debugging a Kotlin/Native crash with symbolicated traces, taking App Store screenshots at required sizes, and testing VoiceOver. A cloud CI covers *shipping* but not *diagnosing*. If that's genuinely off the table: **Codemagic for CI + MacinCloud hourly for the occasional debugging session.**

**Xcode/Kotlin requirements:** KMP tracks recent Xcode closely — a mismatched Xcode SDK breaks framework linking (CMP 1.12 shipped an explicit fix for "build when using old Xcode 16 SDK"). Pin the Xcode version in CI.

**Kotlin/Native compile times — plan for them.** Kotlin→native is inherently much slower than Kotlin→JVM. Mitigations: enable klib **incremental compilation** (Beta, a `gradle.properties` flag); enable **Kotlin/Native caching** (reported ~30 min saving on release builds, little effect on debug); during local dev build the framework for **one architecture only**; and remember **release linking is an order of magnitude slower than debug — only CI needs it.** Keep the shared module lean; every transitive dependency you add is paid for on every iOS link.

**Fastlane** still earns its place for `deliver`/`match` even on Codemagic. Risk of the whole area: **high** — this is the #1 practical blocker for your situation and deserves a spike before any feature work.

Sources: [Codemagic pricing](https://codemagic.io/pricing/) · [GitHub Actions pricing 2026](https://cicdcalculator.com/github-actions) · [Xcode Cloud with KMP](https://www.paleblueapps.com/rockandnull/how-to-setup-xcode-cloud-with-kmm-kmp/) · [Improving Kotlin/Native compilation time](https://kotlinlang.org/docs/native-improving-compilation-time.html) · [Touchlab: build only what you need](https://touchlab.co/touchlab-build-only-what-you-need) · [CI/CD for KMP with GitHub Actions](https://kmpweekly.com/blog/kmp-ci-cd-github-actions)

## 11. Analytics & crash

- **Sentry KMP** (`sentry-kotlin-multiplatform`) — official Sentry SDK, wraps the platform SDKs, native crash reporting + automatic breadcrumbs, one API from `commonMain`. **Recommendation, risk low.**
- **Firebase Crashlytics** — works, but the sharp edge: **Kotlin stack traces on iOS route through the Objective-C runtime and are not auto-desymbolicated**, so you get partial traces without extra tooling. Real pain when debugging a Kotlin/Native crash.
- **Kotzilla** — Koin-ecosystem observability, the closest thing to KMP-native session/DI-graph insight. Complementary, not a replacement.
- **Privacy-friendly analytics:** **TelemetryDeck** (Apple-first, strong privacy story) and **Aptabase** (open source, self-hostable, widely used in KMP because it's a plain HTTP API you can hit from `commonMain` with Ktor). Both avoid IDFA entirely, so **you never have to show the ATT prompt** — which for a pet-health app with GDPR exposure is worth more than the attribution data you'd lose.

**Recommendation: Sentry KMP for crashes + Aptabase (or TelemetryDeck) for product analytics.** Skip Firebase Analytics entirely — it buys you little, pulls in a heavy Android-only SDK, and complicates your EU story.

Sources: [Sentry KMP docs](https://docs.sentry.io/platforms/kotlin-multiplatform) · [Best observability tools for KMP 2026](https://blog.kotzilla.io/the-best-observability-tools-for-kotlin-multiplatform-in-2026) · [Aptabase for KMP/CMP](https://dalen.codes/p/aptabase-analytics-for-kmp-cmp-apps)

## 12. Localization & date/time

**Compose Multiplatform Resources** is the recommendation — it's built in, it's what JetBrains supports, and `composeResources/values-de/strings.xml` is *the same XML format Motivoa already uses*, which makes the port near-mechanical. It handles strings, plurals, fonts, images and raw files. **moko-resources** is the older alternative; it works and supports CMP, but it's a third-party build plugin with a history of rough edges and no dynamic locale updating. Use CMP Resources. Risk **low**.

One gap: CMP Resources has no direct equivalent of `getBestDateTimePattern` — see below.

**Date/time: `kotlinx-datetime`**, now **1.0.0** (stable API). `LocalDate`, `LocalDateTime`, `Instant`, `TimeZone` all in `commonMain`. Two caveats: (1) `java.time` formatting *patterns* don't exist — `kotlinx-datetime`'s `Format` DSL is different and you'll rewrite every formatter; (2) **locale-aware date formatting is still weak** — for "4. Sept." vs "Sep 4" you'll need an `expect fun formatDate(date: LocalDate, skeleton: String): String` with `android.text.format.DateFormat.getBestDateTimePattern` and `NSDateFormatter.dateFormatFromTemplate` as actuals. That's ~30 lines and Motivoa's `DateFormats.kt` already documents exactly why it matters.

Sources: [moko-resources](https://github.com/icerockdev/moko-resources) · [Localization in CMP](https://kotlincodes.com/kotlin/jetpack-compose-kotlin/kotlin-multiplatform/compose-multiplatform-app-localization-a-complete-guide/)

## 13. Testing

**What's testable in `commonTest`:** everything pure — domain logic, streak/schedule computation, serialization, state reducers, ViewModels (with `kotlinx-coroutines-test`), repository logic against a fake DAO. Run with `kotlin("test")`, executed on all targets via `./gradlew allTests`.

**Compose UI tests in `commonTest`:** `runComposeUiTest` works from `commonTest` and, **as of CMP 1.11, `runComposeUiTest` v2 runs those common tests on Android, desktop AND iOS**. Big deal — one UI test suite, three platforms. Two migration notes: the old `runComposeUiTest`/`runSkikoComposeUiTest`/`runDesktopComposeUiTest` APIs are **deprecated**, and 1.11 switched the default dispatcher from `UnconfinedTestDispatcher` to `StandardTestDispatcher` (coroutines no longer auto-advance — existing tests will break).

**Room migration tests** need an instrumented/host-specific harness per platform; `MigrationTestHelper` is Android-side, so keep schema-migration tests in `androidInstrumentedTest` and cover the *logic* in common.

**E2E:** **Maestro** (YAML, Android + iOS, no desktop) is the pragmatic choice; **Parikshan** is the Kotlin-native contender if you want tests in Kotlin across all four targets. Risk **medium** for anything beyond `runComposeUiTest`.

Sources: [Testing CMP UI](https://kotlinlang.org/docs/multiplatform/compose-test.html) · [Test your multiplatform app](https://kotlinlang.org/docs/multiplatform/multiplatform-run-tests.html) · [E2E testing for CMP](https://medium.com/@preetambhosle/e2e-testing-for-compose-multiplatform-5d4666bfc3a3)

## Recommended reference architecture

```
petapp/
├── build-logic/                    convention plugins — one place for KMP/Compose/target config
├── shared/
│   ├── core-model/        common    pure Kotlin data classes, no deps. Everything depends on this.
│   ├── core-common/       common    Result/Either, dispatchers (expect), Clock, UUID (expect)
│   ├── core-datetime/     common    kotlinx-datetime + expect/actual locale-aware formatting
│   ├── core-designsystem/ common    palette, typography, motion tokens, atoms  ← Motivoa port
│   ├── core-database/     common    Room 2.8 entities/DAOs + expect DatabaseBuilder
│   │                      android   actual: Context-based builder
│   │                      ios       actual: NSDocumentDirectory / App Group container path
│   ├── core-datastore/    common    DataStore Preferences + expect path
│   ├── core-network/      common    Ktor client + Supabase client + expect HttpClientEngine
│   ├── core-sync/         common    PowerSync schema, outbox, conflict policy
│   ├── core-notifications/common    expect Scheduler interface (rolling-window materialiser)
│   │                      android   actual: WorkManager + AlarmManager + exact-alarm consent
│   │                      ios       actual: UNUserNotificationCenter
│   ├── core-billing/      common    RevenueCat purchases-kmp wrapper, Entitlement model
│   ├── core-ai/           common    interface AiEngine + UnsupportedAiEngine (the default)
│   │                      android   actual: ML Kit GenAI (Gemini Nano)
│   │                      ios       actual: FoundationModels via Swift shim
│   ├── core-files/        common    FileKit wrapper, image compression, expect share sheet
│   ├── feature-pets/      common    profile, health records
│   ├── feature-log/       common    the shared household care log (event-sourced)
│   ├── feature-reminders/ common
│   ├── feature-household/ common    invites, members, RLS-scoped queries
│   ├── feature-export/    common    PdfKmp report generation
│   ├── feature-paywall/   common    one Compose paywall driven by RevenueCat Offerings
│   └── app-ui/            common    Navigation 3 graph, scaffold, theme host
├── androidApp/                      Application, MainActivity, Glance widgets, FCM service
└── iosApp/                          SwiftUI shell, ComposeUIViewController host,
                                     WidgetKit extension, Sign in with Apple, APNs
```

**Layering rule:** `feature-*` → `core-*` → `core-model`. No feature depends on another feature; cross-feature coordination goes through `core-model` events. Keep `expect/actual` **out of feature modules entirely** — every platform difference lives behind an interface in a `core-*` module. This is the single discipline that keeps iOS from bleeding into your product code.

## Top 10 KMP pitfalls for a solo Android dev going to iOS first time

1. **You need a Mac.** Not for CI — for debugging, profiling, VoiceOver testing and App Store screenshots. Plan and budget it in week 1, not month 6.
2. **`Dispatchers.IO` doesn't exist on Kotlin/Native.** Neither does `Dispatchers.Main` in the same way. You need an `expect val ioDispatcher`. Motivoa's `di/Dispatchers.kt` already has the right shape.
3. **`java.time` is gone.** Every `LocalDate.now()`, `ChronoUnit.DAYS.between()`, `DateTimeFormatter` in your Android code is a rewrite to `kotlinx-datetime`. This will touch more files than you expect — in Motivoa it's **45 files**.
4. **`Context` is everywhere and nowhere.** Every `Context`-taking constructor is an `expect/actual` or a hand-injected platform object. Count them early — it's your porting effort estimate.
5. **iOS back-gesture is not free.** The interactive edge-swipe that every iOS user expects must be explicitly wired. Prototype it before you build 20 screens.
6. **Accessibility is opt-in on iOS Compose.** Skiko draws pixels; VoiceOver sees only what `semantics {}` declares. A custom-drawn UI is invisible to VoiceOver by default. For health records this is also a legal-ish risk.
7. **iOS build times will shock you**, and release linking is ~10× debug. Keep the shared module lean and enable klib incremental + native caching from day one.
8. **iOS has a 64 pending local notification cap** and Android 14+ denies exact alarms by default. Design reminders as *data + a rolling materialiser*, never as a pile of OS alarms.
9. **Every library is a dependency on one maintainer.** Alarmee, Peekaboo, PdfKmp, supabase-kt — all small teams. Wrap each behind your own interface so replacing one is a single file. (Motivoa's `CompanionAiEngine` is the model.)
10. **Resources don't port automatically.** `R.string` → `Res.string` is a mechanical but total rewrite touching every UI file (**108 files** in Motivoa), and plurals/arrays/`getBestDateTimePattern` have different or missing equivalents. Budget it as real work, and do it *before* you have 200 screens.

Bonus 11th: **`System.currentTimeMillis()`, `java.util.UUID`, `java.io.File`, `org.json`, `SimpleDateFormat`, `Locale`** — all JVM-only, all silently compile on Android, all fail the moment you add the iOS target.

---

# JOB 2 — MOTIVOA PORTABILITY AUDIT

**Scope:** single-module Android app, `:app`, 311 Kotlin files, package `com.affirmdaily.motivation`, `namespace`/`applicationId` the same. `settings.gradle.kts` names the root project `MotivationApp`. No modularisation — that's the first structural change a KMP port forces.

## (c) Versions in use

From `gradle/libs.versions.toml`, `app/build.gradle.kts`, `build.gradle.kts`, `gradle.properties`:

| Area | Library | Version | KMP note |
|---|---|---|---|
| Build | AGP | **8.13.2** | fine |
| | Kotlin | **2.2.21** | ⚠️ purchases-kmp needs 2.3.20+; CMP 1.12 wants ≥2.1 (2.2.20+ recommended) |
| | KSP | 2.2.21-2.0.5 | |
| | compileSdk / targetSdk / minSdk | 36 / 36 / **26** | targetSdk 36 required by Play from 2026-08-31 |
| | Java | 17 | |
| | Gradle JVM args | `-Xmx4096m` (raised for R8 + ML Kit GenAI) | Kotlin/Native will want more |
| UI | Compose BOM | **2026.06.01** | maps to CMP ~1.11/1.12 |
| | Navigation Compose | 2.9.8 | → Navigation 3 (1.1.1) |
| | material-icons-extended, ui-text-google-fonts | 1.11.4 | Google Fonts provider is **Android-only** |
| DI | **Hilt** | **2.58** (+ hilt-navigation-compose 1.3.0) | ❌ **Android-only. Full replacement required.** |
| DB | **Room** | **2.8.4**, `exportSchema=true`, schema v**12** | ✅ 2.8.4 already supports iOS |
| Prefs | DataStore Preferences | **1.2.1** | ✅ KMP-capable |
| Async | coroutines-test | 1.11.0 | |
| Serialization | kotlinx-serialization-json | **1.11.0** | ✅ |
| Background | WorkManager | 2.11.2 | ❌ Android-only |
| Widgets | Glance (+appwidget, +material3) | **1.2.0** | ❌ Android-only |
| Billing | **Play Billing KTX** | **7.1.1** | ❌ Android-only; also note Play Billing 8 migration is due |
| Images | **Coil** | **2.7.0** | ⚠️ Coil **2.x is Android-only** — Coil **3** is the multiplatform one |
| Media | media3-exoplayer / ui | 1.11.0 | ❌ Android-only |
| Animation | lottie-compose | 6.7.1 | ❌ Android-only (compottie is the KMP equivalent) |
| Permissions | accompanist-permissions | 0.37.3 | ❌ Android-only |
| Ads | play-services-ads 23.6.0, UMP 3.1.0 | | ❌ Android-only |
| AI | **mlkit genai-prompt** | **1.0.0-beta4** | ❌ Android-only |
| Age | play age-signals | 0.0.4 | ❌ Android-only |
| Firebase | BOM **33.7.0** — analytics, crashlytics, config | | ❌ needs GitLive/KFire or replacement |
| Splash | core-splashscreen | 1.2.0 | ❌ Android-only |
| Net | **retrofit/okhttp declared in TOML but NOT used** — comment says "removed; the app is fully on-device" | | dead entries, drop them |
| Test | JUnit4 4.13.2, espresso 3.7.0, androidx-junit 1.3.0, room-testing, compose ui-test-junit4 | | JUnit4 → `kotlin.test` for commonTest |
| i18n | `strings.xml` × 6 locales (en, ar, de, es, fr, hi), **~17,900 lines total**, `androidResources.localeFilters` + `res/xml/locales_config.xml`, audited by `scripts/i18n_audit.py` | | mechanical port to CMP Resources |

Notably absent: **no kotlinx-datetime** (the app uses `java.time` throughout — 45 files), **no Ktor**, **no Koin**.

## (a) Module / area → reuse strategy

### ✅ Copy as-is to `commonMain` (zero or near-zero change)

| Area | Files | Notes |
|---|---|---|
| **Canvas creature rig** | `companion/ui/CompanionChibi.kt` (1,130 lines), `CompanionCharacter.kt`, `CompanionCreatures.kt`, `CompanionOutfits.kt`, `CompanionAvatar.kt`, `CompanionPlayToy.kt`, `CompanionController.kt`, `CompanionPlayController.kt`, `CompanionShowController.kt` | **Imports are 100% `androidx.compose.ui.graphics.*` + `kotlin.math` — zero `android.*`, zero `java.*`, zero resources.** `docs/COMPANION.md` line 1440 records "Assets in the APK: **none** — the character is Canvas draw calls". Drawn on a 256-unit artboard. **This ports to Compose Multiplatform Canvas verbatim.** |
| **Companion behaviour engines** | `companion/CompanionMood.kt`, `CompanionMotion.kt`, `CompanionFigure.kt`, `CompanionFaceRig.kt`, `CompanionBrain.kt`, `CompanionArchetype.kt`, `CompanionChoreography.kt`, `CompanionRoutine.kt`, `CompanionPolicy.kt`, `CompanionSafety.kt`, `CompanionSignature.kt`, `CompanionSituation.kt`, `CompanionTurn.kt`, `CompanionAttention.kt`, `CompanionArrival.kt`, `CompanionAsk.kt`, `CompanionDiary.kt`, `CompanionProp.kt`, `CompanionAmbientAi.kt`, `CompanionAgeGate.kt`, `CompanionAiGate.kt`, `CompanionPromptBuilder.kt`, `CompanionReviewedReply.kt`, `CompanionCoats.kt`, `CompanionEmberLook.kt` | 25 pure-Kotlin engine files. These are the "pet personality" for a pet app. |
| **Design tokens** | `ui/theme/MotionTokens.kt`, `ui/theme/Color.kt`, `ui/theme/Type.kt` (Type needs the Google Fonts provider swapped for bundled fonts) | |
| **Pure domain engines** | `data/repository/TierEngine.kt`, `PremiumBenefits.kt`, `PledgeFacts.kt`, `ReminderHealth.kt`, `CsvReader.kt`, `audio/VoiceRitual.kt` | |
| **Content catalog** | `data/local/DefaultAffirmations.kt` + `data/local/affirmations/Lib*.kt` (21 files, ~9,883 affirmations) | Pure Kotlin data. Not relevant to a pet app, but proves the pattern for seed content. |
| **UI atoms** | `ui/components/Ambience.kt`, `EmotionIcons.kt`, `HeroComponents.kt`, `HotlineText.kt`; `ui/navigation/Screen.kt` (route sealed class) | |
| **Unit tests** | ~55 of the 70 files under `app/src/test/` — all the `Companion*Test`, `TierEngineTest`, `InsightEngineTest`, `MilestoneEngineTest`, `PledgeFactsTest`, `PremiumBenefitsTest`, `ReminderHealthTest`, `CsvReaderTest`, `PaletteContrastTest` | JUnit4 → `kotlin.test` assertions is the only change. This is a genuinely valuable, unusually large pure-logic test suite. |

### 🔧 Port with changes

| Area | Files | Work required |
|---|---|---|
| **Palette / two-axis theme system** | `ui/theme/MotivoaPalette.kt`, `ui/theme/Theme.kt`, `ui/theme/EmotionStyle.kt`, `ui/theme/Seasonal.kt` | Core data class is pure Compose. Needs: `@StringRes`/`R` name references → CMP Resources; `Theme.kt`'s `java.time` (seasonal) → kotlinx-datetime; dynamic-color and system-bar handling is Android-only. **The `accentFill` rule** (`if (isLight) accentDeep else accent`, with the WCAG reasoning documented inline and pinned by `PaletteContrastTest`) ports unchanged — that's the valuable part. |
| **Room layer** | `data/local/AppDatabase.kt`, `AffirmationDao.kt`, `UserProgressDao.kt`, `UserPreferencesDao.kt`, `CompanionChatDao.kt`; `data/model/UserProgress.kt`, `Affirmation.kt`, `Challenge.kt`, `CompanionChat.kt`; `app/schemas/*.json` | DAOs and `@Entity` classes are already KMP-shaped (Flow-returning, `@Query` strings, `OnConflictStrategy`). Changes: drop `Context` from `getDatabase()` → `expect`/`actual` `RoomDatabase.Builder` + `@ConstructedBy`; `SupportSQLiteDatabase` → `androidx.sqlite.SQLiteConnection` in migrations; `System.currentTimeMillis()` in `MoodEntry.timestamp` → `Clock.System.now()`; **`UserProgress.kt` imports `R` and `androidx.annotation.StringRes` — resource refs must leave the entity**. The **12-version migration history with idempotent `hasColumn()` guards** is exemplary and the *approach* carries over directly. |
| **Streak logic** | `data/repository/UserProgressRepository.kt` | Excellent shape already — `computeStreakUpdate()` is a pure function inside a Room transaction, with the corrupt-date guard and the 2-day grace rule. Only `java.time.LocalDate` → `kotlinx.datetime.LocalDate` and `ChronoUnit.DAYS.between` → `daysUntil`. |
| **Reminder scheduling** | `notifications/SmartNotificationManager.kt`, `QuoteNotificationWorker.kt`, `NotificationReschedulerWorker.kt`, `ReminderHealthProbe.kt`, `notification/NotificationHelper.kt` | The *policy* (time windows, frequency, randomised intervals within a window, distinct notification IDs, distinct PendingIntent request codes, the `ReminderHealthProbe` self-check) is reusable domain logic and should be extracted to `commonMain`. The *mechanism* (WorkManager, NotificationChannel, PendingIntent) is a rewrite behind an `expect` scheduler. Note the manifest comment: no boot receiver, WorkManager persists across reboot — that assumption does not hold on iOS. |
| **Premium gating** | `billing/BillingManager.kt`, `ui/screens/PremiumPaywallScreen.kt`, `data/repository/TierEngine.kt`, `PremiumBenefits.kt` | `TierEngine`/`PremiumBenefits` are already pure → commonMain. `BillingManager` is pure Play Billing → replaced by RevenueCat `purchases-kmp`. **Two functions worth lifting verbatim into commonMain:** `selectOffer()` (picks the offer containing a zero-priced phase, so paywall copy and the launched purchase can't diverge) and `billingPeriodDays()` (ISO-8601 P7D/P1W/P1M/P1Y parser, with the documented `P1Y` bug fix). Both are covered by `BillingManagerTest.kt`. |
| **PDF export** | `utils/TherapyReportGenerator.kt` (drawing) + `data/repository/TherapyReport.kt` (`TherapyReportBuilder`, pure + tested) | **The split is already right**: builder is pure, generator only draws. Builder → commonMain unchanged. Generator: `android.graphics.pdf.PdfDocument` → PdfKmp, or `expect fun render(...)` with the existing Android code as the Android actual. A4-at-72dpi constants and layout logic survive either way. |
| **Backup/export** | `utils/BackupManager.kt` | Already `@Serializable` with kotlinx-serialization and a versioned v1/v2/v3 schema. Needs: `java.util.zip` → a KMP zip lib or `expect`; `SimpleDateFormat` → kotlinx-datetime; `java.io.File`/`Uri` → FileKit; `dagger` → Koin. |
| **UI screens** | all 34 files in `ui/screens/`, 24 in `ui/components/` | Compose code is portable in principle. Blockers are uniform and mechanical: **108 files reference `R.string`/`R.drawable`/`R.raw`**, plus `LocalContext`, `java.time`, Hilt `hiltViewModel()`. Expect this to be the bulk of the labour. |
| **ViewModels** | all 10 in `ui/viewmodel/` + `companion/ui/*ViewModel.kt` | `androidx.lifecycle.ViewModel` + `viewModelScope` are **already multiplatform** (lifecycle-viewmodel 2.10 KMP). Changes: drop `@HiltViewModel`/`@Inject` for Koin, `java.time` → kotlinx-datetime, `R.string` → `Res.string`. Structure (`StateFlow` + `stateIn(WhileSubscribed(5_000))`) is textbook and stays. |
| **Navigation** | `ui/navigation/NavGraph.kt`, `Screen.kt`, `CompanionOverlay.kt` | `Screen.kt` (sealed routes) is portable. `NavGraph.kt` needs Nav2→Nav3, plus the `Settings.Global.ANIMATOR_DURATION_SCALE` reduce-motion check → `expect fun isReduceMotionEnabled()` (iOS: `UIAccessibility.isReduceMotionEnabled`). |
| **i18n** | `res/values*/strings.xml` × 6, `scripts/i18n_audit.py`, `scripts/prerelease.py` | The XML format is compatible with CMP Resources' `composeResources/values-xx/strings.xml`. **The audit scripts are the hidden asset** — they enforce that every shipping locale has every key and that `localeFilters` matches `locales_config.xml`. Re-point the paths and they keep working. |

### ♻️ Rewrite

| Area | Files | Why |
|---|---|---|
| **DI** | `di/AppModule.kt`, `di/CompanionModule.kt`, `di/ComposeEntryPoints.kt`, `di/Dispatchers.kt` — **28 files carry `javax.inject`/`dagger` imports** | Hilt is structurally Android-only. Rewrite as Koin modules. The `@Provides` graph maps almost 1:1 onto Koin's `single { }` — mechanical but touches every constructor's annotations. `Dispatchers.kt` becomes an `expect val`. |
| **Widgets** | `widget/` — 8 files: `AffirmationWidget.kt`, `StreakWidget.kt`, `MoodWidget.kt`, `QuoteWidget.kt`, `FutureSelfWidget.kt`, `JournalPromptWidget.kt`, `WidgetColorProvider.kt`, `WidgetTheme.kt` | Glance stays as the Android actual (unchanged!). iOS needs parallel SwiftUI WidgetKit views. **`WidgetPalette`/`WidgetTheme` — which independently derive the same `accentFill` contrast rule as the app palette — should move to `commonMain` and be read by both.** `StreakWidget.provideGlance()` reading `AppDatabase` directly is exactly the App-Group-shared-DB pattern you want on iOS. |
| **Ads** | `ads/AdManager.kt`, `ads/AdConsent.kt` | AdMob + UMP. Drop entirely for a subscription pet app. |
| **Audio/media** | `audio/AffirmationSpeech.kt` (TTS), `SoundscapeManager.kt` (ExoPlayer), `VoiceNote.kt` (MediaRecorder) | Platform media APIs, full rewrite per platform. `VoiceRitual.kt` (the pure logic) survives. |
| **Share image** | `util/ShareImageUtil.kt`, `util/GrowthStoryShareUtil.kt` | `android.graphics.Bitmap`/`Canvas`/`LinearGradient` + `FileProvider` + `Intent`. Rewrite using Compose `ImageBitmap` + `DrawScope` (which would also make it *more* portable than today) + an `expect` share sheet. |
| **`Strings` abstraction** | `utils/Strings.kt`, `utils/ContextStrings.kt` (in AppModule), `utils/TestStrings.kt` | The **interface design is excellent and should be preserved verbatim** — engines look up ids, never concatenate. But the signatures are `@StringRes id: Int`, which doesn't exist in CMP Resources (`StringResource` objects instead). Change the type parameter, keep the pattern. |
| **`DateFormats`** | `utils/DateFormats.kt` | Depends on `android.text.format.DateFormat.getBestDateTimePattern`. Becomes `expect fun bestPattern(locale, skeleton)` — and the doc comment explaining *why* skeletons beat patterns (a German reader shown "Sep 4, 2026 9:00 PM") is the spec for the iOS actual (`NSDateFormatter.dateFormatFromTemplate`). |

### 📱 Android-only `actual` (keep the code, wrap it)

| Area | Files | Interface |
|---|---|---|
| **On-device AI** | `companion/CompanionAiEngine.kt` (**interface, already pure, already in commonMain-ready form**) + `MlKitCompanionAiEngine.kt` (ML Kit Gemini Nano) + `UnsupportedCompanionAiEngine` (the object default) | **This is the single best-designed thing in the repo for KMP purposes.** The interface already models `Availability.{Ready,NeedsDownload,Downloading,Unsupported}`, a `Download` progress flow, a streaming `reply(prompt): Flow<String>`, and `close()`. `UnsupportedCompanionAiEngine` makes "no AI" a first-class, tested path. Drop `MlKitCompanionAiEngine` into `androidMain` and write an `iosMain` actual over Apple FoundationModels — **the abstraction needs no change at all.** |
| **Crash/analytics** | `analytics/AnalyticsManager.kt` (interface) + `FirebaseAnalyticsManager`, `crash/CrashReporter.kt` (already pure!), `config/RemoteConfigManager.kt` | `AnalyticsManager` is already an interface with a Firebase impl — swap the impl for Aptabase/Sentry. |
| **Age signals / safety** | `companion/AgeSignalsProvider.kt`, `CompanionAgeManager.kt`, `safety/SafetyManager.kt` | Play Age Signals is Android-only; `CompanionAgeGate.kt` (the policy) is already pure. |
| **Data erasure** | `data/local/DataEraser.kt` | File paths + `clearAllTables()`; interface in common, actuals per platform. |
| **Permissions** | accompanist-permissions usages, `ui/screens/NotificationSetupScreen.kt` | `expect` permission requester. |

## (b) The 5 highest-value things to carry over

1. **The Canvas-drawn creature rig — `companion/ui/CompanionChibi.kt` + `CompanionCreatures.kt` + `CompanionOutfits.kt` + `CompanionCharacter.kt`.** 1,130 lines of pure `DrawScope` on a 256-unit artboard, **zero assets, zero Android imports, nine species, coat/marking lookup tables, a face rig, outfits and held props** — plus a documented secondary-motion model (`HEAD_LAG 5°`, `EAR_LAG 9°`, `TAIL_LAG 14°`, `WING_LAG 11°`, tail carry as posture vs. tail swing as mood) and ~20 dedicated unit tests (`CompanionFigureTest`, `CompanionGaitTest`, `CompanionSecondaryMotionTest`, `CompanionTurnTest`, `CompanionOarsTest`…). **This drops into Compose Multiplatform Canvas essentially unchanged and is worth months of work.** For a pet-care app it is almost literally the product: you already have a drawn cat, dog, fox, hamster, bear and dragon with coats and moods. Nothing else in the audit comes close in value-per-line-ported.

2. **The `CompanionAiEngine` interface pattern** (`companion/CompanionAiEngine.kt`). A correctly-shaped `expect/actual` boundary written *before* anyone asked for one, with an explicitly-supported "this device can't do it" path and a streaming-flow API. Copy this file, rename it, and you have the on-device AI architecture from §8 of Job 1 for free — Gemini Nano on Android, Apple Foundation Models on iOS, rules-based everywhere else.

3. **The `accentFill` / two-axis palette contrast system** (`ui/theme/MotivoaPalette.kt` + `widget/WidgetTheme.kt` + `ui/theme/PaletteContrastTest.kt`). A theming model where any of 13 colours works in either of 2 modes, with a **hard-won, measured WCAG rule** — never fill a surface with `accent`, always `accentFill = if (isLight) accentDeep else accent`, because 9 of 13 themes fail AA otherwise (Frost measured 2.95:1) — and a unit test pinning it. The gradient variant derives its far stop by darkening precisely so a future 14th theme cannot reintroduce the bug. This is institutional knowledge you cannot re-derive cheaply, and it's pure Compose.

4. **The Room migration discipline** (`data/local/AppDatabase.kt`, `app/schemas/`, `androidTest/.../MigrationTest.kt`, `SeedingTest.kt`). Twelve versions, every `ADD COLUMN` guarded by an idempotent `hasColumn()` PRAGMA check, `IF NOT EXISTS` on creates copied verbatim from Room's own generated `createSql`, `IF EXISTS` on the one destructive drop, `fallbackToDestructiveMigrationFrom(1,2,3)` only for pre-v5 users, exported schemas under version control and wired into `androidTest` assets. Plus the `seedMutex` fix for the real onCreate/onOpen race that duplicated 9,883 affirmations on a Galaxy S25. For a pet **health records** app where data loss is unforgivable, carrying this discipline over is worth more than carrying any specific code.

5. **The reminder policy + self-check, and the `Strings`/`DateFormats` i18n architecture.** Two things, both about shipping quality: (a) `SmartNotificationManager` + `QuoteNotificationWorker` + `ReminderHealthProbe` encode real learned constraints — distinct notification IDs so reminders don't replace each other in the shade (the 1006-not-1001 bug), distinct PendingIntent request codes so an action can't hijack a body tap, randomised intervals within a user-set window, and a health probe that asks the scheduler what's actually queued. (b) `Strings.kt`'s rule — *an engine may only look up an id, never concatenate two looked-up sentences or case-change one, because that's grammar and grammar belongs in the resource file* — is the reason 6 locales × ~2,950 strings are actually correct rather than nominally translated. Both are policy, not code, and both survive the port.

## Structural blockers, sized

| Blocker | Scale | Effort |
|---|---|---|
| `R.string`/`R.drawable`/`R.raw` → CMP Resources | **108 files** | Large, mechanical |
| `java.time` → kotlinx-datetime | **45 files** | Medium, mechanical + a few semantic traps |
| `android.content.Context` in constructors/params | **39 files** | Medium — each is a design decision |
| Any `android.*` import | **65 files** | — |
| Hilt/`javax.inject` → Koin | **28 files** | Medium, mechanical |
| Single `:app` module → multi-module KMP | whole repo | Do this **first**, on Android only, before adding the iOS target |

**Suggested sequencing:** (1) split `:app` into the module graph while still Android-only — this alone surfaces every layering violation; (2) swap Hilt→Koin and Coil 2→3 on Android; (3) migrate `java.time`→kotlinx-datetime; (4) migrate resources; (5) *only then* add the `iosArm64`/`iosSimulatorArm64` targets. Each step is independently shippable to Play, which matters enormously for a solo developer.

Sources: [Room for KMP](https://developer.android.com/kotlin/multiplatform/room) · [DataStore for KMP](https://developer.android.com/kotlin/multiplatform/datastore) · [Koin KMP setup](https://insert-koin.io/docs/reference/koin-core/kmp-setup/) · [purchases-kmp](https://github.com/RevenueCat/purchases-kmp) · [Navigation 3 in CMP](https://kotlinlang.org/docs/multiplatform/compose-navigation-3.html) · [Exact alarms on Android 14+](https://developer.android.com/about/versions/14/changes/schedule-exact-alarms)

**Nothing in the repository was modified** — all operations were reads (`cat`, `head`, `find`, `grep`, `wc`, `ls`).