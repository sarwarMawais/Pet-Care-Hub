# Pet Care Hub — Plan v1 (2026-09-15)

Status: **plan only, implementation not started.** Full plan page: `pet-care-hub-plan.html` in this folder (published: https://claude.ai/artifact/VaMa9VqJyBNvxiUST5zWT8). Research behind it: the three `research-*.md` files here.

## Context
The user explored Trainline-style rail booking (unbuildable solo: licensed retail business), Vinted-style marketplaces (liquidity + unit economics), and a pharma B2B SaaS (declined — user does not want a pharma app). They chose a **consumer app people love and keep paying for**, and picked the pet care category. This plan is the result of research into what owners want, what competitors do, store policies, and the KMP stack.

## The thesis (validated)
Nobody combines **shared household daily logging** (who fed/walked/medicated, when) with a **real portable health record** (vaccinations, meds, weight, visits, documents). Users install 3–5 apps in sequence trying to assemble one. The incumbent (11pets, 500K+ installs) is at **2.2★** after a €69/yr paywall + botched migration, publishes a data-export file, and **nobody offers an importer**.

## Honest ceiling
No standalone pet-record app has passed 1M installs via consumer distribution (PetDesk did via vet clinics). Target: **100–300K installs, 3–6K paying households in 24 months** (~$90K net/yr). Growth: ASO on high-intent queries, "Import from 11pets" bridge, household-invite loop, later vet/sitter channel.

## Product laws (each answers a documented failure)
1. Unlimited pets in free tier, forever. 2. Archiving a deceased pet is free and never uses a slot; no upsell. 3. Sync near-real-time and attributed. 4. Reminders are data; OS holds a rolling window; exact times, quiet hours, honest fallback. 5. Documents open offline in-app. 6. Exports shaped per job (boarding cert, new-vet handover, insurance claim pack, sitter brief, travel). 7. Free-text fallback on every dropdown; species-scoped tasks. 8. Offline-first reads and writes. 9. Never retract a free feature. 10. Reply within 24h. 11. Never child-directed art/copy (avoid Families policy). 12. "Record", never "diagnose"; no dose calculators.

## Pricing
Free (unlimited pets, log, record, reminders, 3 members, 20 docs, 1 export/mo, widgets) · **Household $29.99/yr** (unlimited members + time-boxed sitters, unlimited docs, all exports, albums, custom fields/graphs, CSV; 7-day trial) · $3.99/mo · **Lifetime $79.99**. Per household, never per pet/person. Paywall built to Apple 3.1.2 + Play subscription rules.

## Screens (v1 unless noted)
Onboarding · Sign in (Apple/Google/email magic link) · Today feed + quick log · Pet profile · Health record · Medications & schedules · Reminder reliability/permissions · Documents vault + viewer · Household & members · Sitter mode + brief · Exports · Journal & monthly album · Paywall · Settings/privacy/deletion · Archive & memorial · Report/block · Import (11pets) · Widgets (Glance + WidgetKit) · **v2:** Travel readiness, lost-pet poster, Watch/Wear, professional sitter mode, supply counters, vet-visit prep (rules → on-device AI).

## Stack
KMP + Compose Multiplatform 1.12 · Navigation 3 (fallback Decompose) · Room KMP 2.8 · DataStore · **Supabase (EU region)** + **PowerSync** · RevenueCat purchases-kmp · Alarmee + own rolling-window scheduler · FCM/APNs thin actuals · FileKit, Coil 3, Peekaboo · PdfKmp (platform-actual fallback) · Koin 4.1 · kotlinx-datetime · Compose Resources (en, de, fr, es) · Sentry KMP + Aptabase (no ATT) · AI behind `AiEngine` interface, Unsupported default · Codemagic + a Mac mini (cannot build iOS from Windows).
Sync model: append-only `CareEvent` log (UUID, author, occurred_at, household_id) — union merge; tombstones; LWW only on rare edits; RLS by household. Reminders: `ScheduleRule` is truth; materialiser keeps 7 days (Android) / ≤40 slots (iOS); `SCHEDULE_EXACT_ALARM` only.

## Reuse from Motivoa (copy in, don't port wholesale)
Canvas creature rig (`companion/ui/CompanionChibi.kt` etc., zero Android imports) · `CompanionAiEngine` interface · `accentFill` palette contrast rule + `PaletteContrastTest` · Room migration discipline · reminder policy + `ReminderHealthProbe`, `Strings`/`DateFormats` patterns.

## Compliance — top 8
Exact alarms (Play, critical) · Paywall disclosure 3.1.2 (Apple, critical) · Health apps declaration ("no health features" + ship disclaimer; verify) · UGC obligations with no private-group exemption (both) · **Register as Organization** (skips 12-tester/14-day rule; needs D-U-N-S) · DSA trader status with business address (both) · European Accessibility Act (EU law) · Apple 4.3(b) crowded-category rule (differentiate in v1).
Full checklist in the HTML page §Store compliance and `research-store-policies.md`.

## Roadmap
- **Wk 0–3 Phase 0:** entity + D-U-N-S + Organization enrolment; Mac + Codemagic; signed iOS hello-world; spikes: Nav3 swipe-back, Room+PowerSync+Supabase two-device round-trip, exact-alarm consent + iOS 64-slot materialiser; Play policy question on USE_EXACT_ALARM.
- **Wk 4–13 Phase 1:** daily loop + household + meds + reminders + widgets; 30-household closed beta; ship nothing until reminders are boringly correct.
- **Wk 14–22 Phase 2:** record, documents, journal, memorial, exports, paywall, report/block, 11pets import; compliance pass; launch both stores; Reddit community launch.
- **Mo 6–9 Phase 3:** sitter pro mode, travel readiness, supply counters, vet prep, Watch/Wear, vet/sitter channel.
- **Mo 9+:** web portal, wearable bridge, more locales; EU alt billing only past ~$500K ARR.

## Open decisions
Final name (trademark clearance) · species scope (recommend dog+cat templates + full "Other") · lifetime price / free doc cap · EU legal review · finish Motivoa's billing + Play submission before Phase 1.
