# Reusing code from Motivoa

**Source repository:** `C:\Users\MuhammadSarwar\AndroidProjects\Motivoa`
**Source root:** `app/src/main/java/com/affirmdaily/motivation/`
**Shape:** single-module Android app (`:app`), 311 Kotlin files, Hilt + Room 2.8 + Compose, `minSdk` 26 / `targetSdk` 36.

All paths below were verified to exist on **16 September 2026**. Line counts are from that date.

---

## The rule

**Copy these five assets in. Do not port the app wholesale.**

Motivoa is Android-only, single-module and Hilt-based. Migrating it would mean touching 108 files for resources, 45 for `java.time`, 39 for `Context`, and 28 for DI — before writing a single line of pet-care code. Starting fresh and lifting the five things that are genuinely valuable is faster and leaves Motivoa shippable on its own timeline.

---

## 1. The Canvas creature rig — highest value

**What it is:** a complete drawn-animal rendering system. Pure `DrawScope` on a 256-unit artboard. Nine species with coats and markings, a face rig, outfits, held props, and a documented secondary-motion model.

**Why it ports cleanly:** zero assets, zero `android.*` imports, zero resource references. It is Compose graphics and `kotlin.math`, nothing else. It drops into Compose Multiplatform Canvas essentially unchanged.

| File | Lines | Role |
|---|---|---|
| `companion/ui/CompanionChibi.kt` | 1,213 | The rig itself — **the only live animal rig** |
| `companion/ui/CompanionCharacter.kt` | — | Per-character definitions |
| `companion/ui/CompanionCreatures.kt` | — | ⚠️ **Half of this is legacy.** Check before copying |
| `companion/ui/CompanionOutfits.kt` | — | Form-independent overlays |
| `companion/ui/CompanionAvatar.kt` | — | Small circular-clipped variant |
| `companion/CompanionFigure.kt` | — | Proportion guard — prevents the stale-proportion bug class |
| `companion/CompanionFaceRig.kt` | — | Eyes, expression |
| `companion/CompanionMotion.kt`, `CompanionCoats.kt` | — | Secondary motion, coat lookup |

**Where it goes:** `shared/core-designsystem/` (the drawing primitives) and `shared/feature-pets/` (the avatar composable).

**How we use it:** an **optional avatar** for each pet that reflects the pet's day — content after a walk, expectant near meal time. Adult art direction. **Never a mascot with big cartoon eyes** — that would drag us into Play's Families policy (see law 11 in `CLAUDE.md`).

**Before you touch it:** Motivoa's own `docs/COMPANION.md` is the canonical reference, and there are ~20 unit tests (`CompanionFigureTest`, `CompanionGaitTest`, `CompanionSecondaryMotionTest`, `CompanionTurnTest`) worth copying alongside. There are also eight counter-intuitive drawing laws recorded there — read them before editing geometry.

---

## 2. The `CompanionAiEngine` interface — the expect/actual pattern

**File:** `companion/CompanionAiEngine.kt` (82 lines)
**Companions:** `companion/MlKitCompanionAiEngine.kt` (Gemini Nano), and `UnsupportedCompanionAiEngine` (the object default)

**Why it matters:** it is a correctly-shaped platform boundary written before anyone asked for one. It already models:

- `Availability.{Ready, NeedsDownload, Downloading, Unsupported}`
- A download progress `Flow`
- A streaming `reply(prompt): Flow<String>`
- `close()`

And critically, **`UnsupportedCompanionAiEngine` makes "this device has no AI" a first-class, tested path** rather than an afterthought. On-device AI is available to a minority of devices on both platforms, so the no-AI path is the common case.

**Where it goes:** `shared/core-ai/`. Rename to `AiEngine` / `UnsupportedAiEngine`.

- `androidMain` actual → ML Kit GenAI (Gemini Nano), lifted from `MlKitCompanionAiEngine.kt`
- `iosMain` actual → Apple Foundation Models via a Swift shim

**The abstraction needs no change.** This is also the pattern to copy for every other single-maintainer library wrapper (Alarmee, PdfKmp, Peekaboo).

---

## 3. The `accentFill` palette contrast rule

| File | Lines | Role |
|---|---|---|
| `ui/theme/MotivoaPalette.kt` | 305 | The palette model |
| `ui/theme/MotionTokens.kt` | 50 | Motion timings |
| `widget/WidgetTheme.kt` | 142 | Independently derives the same rule for widgets |
| `widget/WidgetColorProvider.kt` | — | Widget colour plumbing |
| `ui/theme/PaletteContrastTest.kt` | — | **The test that pins the rule** |

**The rule, and why it exists:**

> Never fill a content-bearing surface with `accent`. Always use
> `accentFill = if (isLight) accentDeep else accent`.

Because **9 of 13 themes fail WCAG AA otherwise** — one was measured at 2.95:1. The gradient variant derives its far stop by darkening precisely, so adding a 14th theme cannot reintroduce the bug. A unit test enforces it.

This is measured, hard-won knowledge that cannot be cheaply re-derived, and it is pure Compose. **Copy the rule, the test, and the reasoning comments together** — the comments are the spec.

**Where it goes:** `shared/core-designsystem/`. Move the widget variant into common so Glance and WidgetKit both read one source of truth.

**Also relevant:** the European Accessibility Act requires ≥ 4.5:1 contrast. This rule is compliance, not taste.

---

## 4. Room migration discipline

| Path | Role |
|---|---|
| `data/local/AppDatabase.kt` (323 lines) | Twelve versions of migrations |
| `app/schemas/com.affirmdaily.motivation.data.local.AppDatabase/` | Exported schemas, in version control |
| `app/src/androidTest/.../MigrationTest.kt` | Migration tests wired to those schemas |
| `app/src/androidTest/.../SeedingTest.kt` | Catches the double-seed race |

**Copy the discipline, not the code.** The practices worth carrying:

- Every `ADD COLUMN` guarded by an idempotent `hasColumn()` PRAGMA check
- `IF NOT EXISTS` on creates, copied verbatim from Room's own generated `createSql`
- `IF EXISTS` on the one destructive drop
- `fallbackToDestructiveMigrationFrom(...)` only for versions nobody is on any more
- Exported schemas committed and wired into instrumented tests
- The `seedMutex` fix for a real `onCreate`/`onOpen` race that duplicated 9,883 rows on a physical Galaxy S25 — a bug no emulator exposed

**Why it matters here more than there:** this is a **health records** app. Data loss is unforgivable, and the 11pets collapse was substantially a migration failure. Users locked out of seven years of records is the exact outcome to design against.

**Where it goes:** `shared/core-database/`. Note that Room KMP migrations use `androidx.sqlite.SQLiteConnection` rather than `SupportSQLiteDatabase`, so the mechanism changes slightly — the discipline does not.

---

## 5. Reminder policy and i18n architecture

### Reminders

| File | Lines | Carries |
|---|---|---|
| `notifications/SmartNotificationManager.kt` | 432 | Time windows, randomised intervals, scheduling policy |
| `notifications/ReminderHealthProbe.kt` | — | **Asks the OS what is actually queued** and compares |
| `notifications/QuoteNotificationWorker.kt` | — | WorkManager pattern |
| `notifications/NotificationReschedulerWorker.kt` | — | Re-materialisation after reboot |
| `notification/NotificationHelper.kt` | — | Channels |

Learned constraints encoded in these files, all worth carrying:

- **Distinct notification IDs** so reminders do not replace each other in the shade (the "1006 not 1001" bug)
- **Distinct PendingIntent request codes** so an action cannot hijack a body tap
- Randomised intervals within a user-set window
- A health probe that verifies the queue rather than trusting it

⚠️ **What does not carry:** Motivoa has no boot receiver because WorkManager persists across reboot on Android. **That assumption does not hold on iOS.** See [`adr/0004`](adr/0004-reminders-are-data.md).

**Where it goes:** the *policy* into `shared/core-notifications/` common code; the *mechanism* (WorkManager, AlarmManager, PendingIntent) into the Android actual.

### i18n

| File | Lines | Role |
|---|---|---|
| `utils/Strings.kt` | 166 | The abstraction — engines look up ids, never build sentences |
| `utils/ContextStrings.kt` | — | Android implementation |
| `utils/TestStrings.kt` | — | Test double |
| `utils/DateFormats.kt` | 49 | Why skeletons beat patterns |
| `scripts/i18n_audit.py` | — | Enforces key parity across locales |

**The rule worth carrying verbatim:**

> An engine may only look up a string id. It may never concatenate two looked-up sentences, and never case-change one — because that is grammar, and grammar belongs in the resource file.

That rule is why six locales × ~2,950 strings are actually correct rather than nominally translated.

`DateFormats.kt`'s doc comment explains why `getBestDateTimePattern` matters — a German reader shown "Sep 4, 2026 9:00 PM" is the failure it prevents. **That comment is the spec for the iOS actual** (`NSDateFormatter.dateFormatFromTemplate`).

⚠️ **Signature change required:** Motivoa's `Strings` takes `@StringRes id: Int`. Compose Resources uses `StringResource` objects instead. **Change the type parameter, keep the pattern.**

**Where it goes:** `shared/core-datetime/` and the string abstraction into `shared/core-common/`. Re-point `i18n_audit.py` at `composeResources/` and it keeps working.

---

## Also worth lifting, smaller

| From | What | Why |
|---|---|---|
| `billing/BillingManager.kt` | `selectOffer()` and `billingPeriodDays()` | `selectOffer` picks the offer containing a zero-priced phase so paywall copy and the launched purchase cannot diverge. `billingPeriodDays` is an ISO-8601 P7D/P1W/P1M/P1Y parser with a documented `P1Y` bug fix. Both are pure, both are tested |
| `utils/TherapyReportGenerator.kt` (461 lines) + `data/repository/TherapyReport.kt` | The builder/generator split | **The split is already correct**: the builder is pure and tested, the generator only draws. Copy the shape for our shaped exports — builder into `feature-export` common, generator behind an `expect` |
| `data/repository/UserProgressRepository.kt` | `computeStreakUpdate()` | A pure function inside a Room transaction, with a corrupt-date guard and a grace rule. Good shape to imitate for adherence tracking |
| `di/Dispatchers.kt` | The dispatcher indirection | Already the right shape for `expect val ioDispatcher` |
| `widget/StreakWidget.kt` | Glance reading Room directly | Exactly the App-Group-shared-database pattern iOS needs |

---

## Do not copy

| | Why |
|---|---|
| `ads/` (AdManager, AdConsent) | AdMob. This is a subscription app with no ads |
| `audio/` (TTS, ExoPlayer soundscapes, MediaRecorder) | Platform media APIs, full rewrite per platform, and not in scope |
| `di/` Hilt modules (28 files carry `javax.inject`) | Hilt is structurally Android-only. Rewrite as Koin |
| `data/local/DefaultAffirmations.kt` + `affirmations/Lib*.kt` | 9,883 affirmations. Irrelevant content, though it proves the seed-content pattern |
| `companion/AgeSignalsProvider.kt` | Play Age Signals is Android-only |
| `util/ShareImageUtil.kt` | `android.graphics.Bitmap` + FileProvider. Rewrite with Compose `ImageBitmap` — which would be more portable anyway |

---

## Sized effort, if you were tempted to port instead

| Blocker | Files affected |
|---|---|
| `R.string` / `R.drawable` / `R.raw` → Compose Resources | **108** |
| `java.time` → kotlinx-datetime | **45** |
| `android.content.Context` in constructors or params | **39** |
| Any `android.*` import | **65** |
| Hilt / `javax.inject` → Koin | **28** |

This table is the argument for copying five assets rather than migrating an app.

---

## Before you open Motivoa

It has its own `CLAUDE.md` and `docs/COMPANION.md` with conventions that matter if you edit anything there — particularly the drawing laws and the "one rig" rule (a second legacy rig was deleted in commit `52b0136`). **This project should not modify Motivoa.** Read from it, copy out of it, leave it alone.
