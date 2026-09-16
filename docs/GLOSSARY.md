# Glossary

Terms used across the docs and the code, in the sense this project means them.

---

## Product concepts

**Household** — the unit of ownership and billing. Contains pets and members. **Everything is scoped by `household_id`**, including sync and row-level security. One subscription covers the whole household regardless of how many pets or people are in it.

**Member** — someone with ongoing access to a household. Roles: **Owner** (one, can transfer or dissolve), **Member** (full access), **Sitter** (time-boxed, limited).

**Sitter** — a guest with an expiry date. Sees today's routine and can log against it; may or may not see the health record and documents, at the owner's choice. Never charged, never shown the paywall, never marketed to. Access ends server-side at the chosen time.

**Sitter brief** — the simplified view a sitter gets: today's checklist, feeding amounts, medication instructions, vet and emergency contacts, house notes. Also exportable as a PDF for sitters who do not install the app.

**Care log** — the append-only stream of everything anyone did for a pet. See `CareEvent`.

**Daily loop** — the habit-forming half of the product: feeding, walks, medication, quick logging. Distinct from the record.

**Record** — the lifetime half: vaccinations, visits, conditions, labs, weight, documents. Accumulates as a byproduct of the daily loop.

**Shaped export** — a PDF laid out for a specific reader, rather than one generic dump. Five of them: **boarding certificate**, **new-vet handover**, **insurance claim pack**, **sitter brief**, **full backup**. The distinction matters — nobody else in the category does this.

**Claim pack** — the insurance export: selected visit, itemised invoice, relevant history, condition timeline, proof-of-payment slot. Aimed at the ~12–18% of pet insurance claims denied for missing paperwork.

**Travel readiness** — computed checklist for taking a pet across a border, derived from records we already hold. See *AHC*.

**Memorial / archive** — a pet that has died (memorial) or left our care (archive). Keeps every record, leaves the Today screen, **never consumes a paid slot, never shows a paywall**. See law 2 in `CLAUDE.md`.

**Gotcha day** — the date a pet was adopted, tracked alongside birthday. A small thing users love and specifically ask for.

**Quiet hours** — a per-household window (default 22:00–07:00) during which non-critical reminders do not fire. Individual medications can be flagged critical to ring through.

---

## Data model

**`CareEvent`** — one immutable row per thing that happened: fed, walked, dose given, weight taken, note. Carries a client-generated UUID, `occurred_at` (the real time), `author_id` (who), `household_id` (scope). **Never mutated** — an edit writes a new row with `supersedes` set; a delete sets `deleted_at` as a tombstone. This is what makes offline merges conflict-free. See [`adr/0003`](adr/0003-append-only-care-log.md).

**`ScheduleRule`** — the definition of a recurring thing: which pet, which medication or task, explicit clock times, day pattern, window, paused flag, critical flag. **The truth about reminders.** Occurrences are derived from it, never stored.

**`DueItem`** — a computed (not stored) occurrence of a `ScheduleRule` that is due now or soon. What the Today screen and widgets display.

**Materialiser** — the component that converts `ScheduleRule`s into a rolling window of actual OS notifications (7 days on Android, ≤ 40 slots on iOS). Re-runs on foreground, background refresh, reboot, timezone change, and after any member marks a dose done. See [`adr/0004`](adr/0004-reminders-are-data.md).

**Tombstone** — a row marked deleted rather than removed, so a late-syncing delete cannot resurrect data.

**Supersedes** — the pointer from an edited event to the row it replaces. Preserves history, which a health record wants anyway.

---

## Technical

**KMP** — Kotlin Multiplatform. Shared Kotlin compiled for both Android (JVM) and iOS (Native).

**CMP** — Compose Multiplatform. JetBrains' Compose for iOS, desktop and web as well as Android. iOS has been stable since 1.8.

**`expect` / `actual`** — the KMP mechanism for a common declaration with per-platform implementations. **In this project it is confined to `core-*` modules** and never appears in a feature.

**`commonMain` / `androidMain` / `iosMain`** — the source sets. `commonMain` compiles for both platforms; the others are platform-specific.

**Skiko** — the Skia-based renderer Compose uses on iOS. It draws pixels, which is why **VoiceOver sees only what `semantics {}` explicitly declares**.

**RLS** — Postgres Row-Level Security. Server-side predicates that filter every table by household membership. Access control lives here, not in the client.

**PowerSync** — the offline-first sync engine between local SQLite and Supabase Postgres. Provides the queue, retry and partial sync we would otherwise hand-write.

**Partial sync** — syncing only the rows a user can see (`WHERE household_id IN (my memberships)`) rather than the whole table.

**App Group** — the iOS mechanism letting the app and its widget extension share a container. Our SQLite file lives there so the widget can read it.

**Glance** — Android's Compose-like API for home-screen widgets. Android-only; iOS uses WidgetKit in SwiftUI. The one place the view layer is written twice.

**`accentFill`** — the palette rule inherited from Motivoa: never fill a content-bearing surface with `accent`; use `accentDeep` in light mode. Nine of thirteen themes fail WCAG AA otherwise.

**Materialised view** — a precomputed "current state" (latest weight, last fed) derived from the event log, so the UI does not fold thousands of events on every render.

---

## Store and legal

**ADR** — Architecture Decision Record. One file per decision that is expensive to reverse. See [`adr/`](adr/).

**`SCHEDULE_EXACT_ALARM`** — the Android permission we use for medication reminders. Denied by default from Android 14; the user must grant it manually.

**`USE_EXACT_ALARM`** — the auto-granted alternative, **restricted by Play policy to alarm-clock and calendar apps**. We do not qualify, and declaring it **blocks the upload**. Never add it.

**Photo Picker / SAF** — Android's permissionless ways to select images (`PickVisualMedia`) and documents (`ACTION_OPEN_DOCUMENT`). Using them is why we need no media permissions at all.

**Privacy manifest (`PrivacyInfo.xcprivacy`)** — required Apple file declaring collected data types and "required reason API" usage. Missing or wrong = rejected at upload, not at review.

**Nutrition Labels** — Apple's App Store privacy disclosures. Must match the privacy manifest and the actual behaviour.

**Data safety form** — Play's equivalent. Must match the privacy policy, the Nutrition Labels, and reality — mismatches are a top enforcement trigger.

**DSA trader status** — EU Digital Services Act requirement to declare and publicly display trader contact details. Not declaring it means removal from the EU on both stores. **Never use a home address.**

**EAA** — European Accessibility Act, enforceable since 28 June 2025. Applies to consumer apps selling into the EU. Technical standard is EN 301 549 (WCAG 2.1 AA). A **law**, not a store policy — neither store will warn you.

**UGC** — user-generated content. Our household feed counts, and **neither store offers a private-group exemption**: report, block, moderation and published contact info are all required.

**Apple 4.3(b)** — the June 2026 guideline letting Apple reject *and remove* apps "indistinguishable" from existing ones in crowded categories. Our defence is household sharing, sitter access, interactive widgets and claim packs, all shipped in v1.

**Small Business Program** — Apple's 15% commission tier for developers under $1M proceeds. Must be enrolled in; not automatic.

**D-U-N-S number** — the business identifier required to enrol as an Organization on both stores. Free, but allow 5–14 business days.

---

## Domain

**AHC** — Animal Health Certificate. Required for each UK→EU pet trip since Brexit, costs £150–250, **valid for one trip only**, must be issued within 10 days of travel. Rabies vaccination must be ≥ 21 days old and administered *after* microchipping. Dogs returning to the UK need tapeworm treatment 24–120 hours before arrival. All of these are computable from records we already hold — hence *travel readiness*.

**Preventative** — a routine parasite treatment (flea, tick, worm), usually monthly. Modelled as a `ScheduleRule` with a supply counter.

**Lepto4, DHPP, Bordetella, Rabies** — common vaccinations. Note that grouped names like Lepto4 must **stay grouped**; splitting them into four entries is a specific complaint against a competitor.

**Titre test** — a blood test measuring immunity, sometimes used instead of revaccination. Stored as a lab result.

**Adherence** — whether doses were actually given on schedule. Shown as a 14-day dot strip; a gap is a fact to show the vet, never a judgement on the owner.

---

## Competitors named throughout

**11pets** — the incumbent. 500K+ installs, **2.2★**, wounded by a €69/yr paywall and a botched migration. Publishes a data-export file that nobody has written an importer for. Our primary acquisition target.

**PetDesk** — the only pet-record app past 1M installs, achieved via **vet-clinic distribution**, not consumers. Owners cannot edit their own data.

**DogLog** — the best shared-care app (100K+, 4.5★), undermined by sync that needs an app restart and notifications 10–60 minutes late.

**dogcat.app (Pet Care Tracker)** — a solo developer at 100K+ installs who **wins ASO** on all three of our key queries. The most replicable playbook, and the most underrated competitor.

**Dogo** — the category's commercial ceiling: ~$1.8M ARR at 5M downloads, selling *training*, which has a stronger hook than record-keeping.
