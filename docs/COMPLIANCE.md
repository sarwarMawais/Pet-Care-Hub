# Store compliance checklist

Actionable pre-submission list. Reasoning, severity and sources are in [`PLAN.md`](PLAN.md) §9; the full audit with policy citations is [`research/store-policies.md`](research/store-policies.md).

Audit date: **15 September 2026.** Re-verify anything marked ⚠️ before submitting — several items were in flux.

---

## The eight that are most likely to bite

| | Risk | Store | Answer |
|---|---|---|---|
| 🔴 | Medication reminders vs exact alarms — we do **not** qualify for `USE_EXACT_ALARM` | Play | `SCHEDULE_EXACT_ALARM` + consent flow + honest inexact fallback. **Spike before any feature work.** |
| 🔴 | Paywall disclosure (Apple 3.1.2) — the most common subscription rejection | Apple | Price most prominent, renewal sentence, trial end date, Restore, ToU + Privacy on the sheet |
| 🟠 | Health apps declaration — mandatory for every app; pet health is not addressed ⚠️ | Play | Declare "no health features", ship the disclaimer anyway, keep written reasoning |
| 🟠 | Household sharing is UGC — **no private-group exemption exists** | Both | Report on every item, report/block member, terms before first upload, 24h abuse inbox |
| 🟠 | 12 testers / 14 days closed test for new personal accounts | Play | **Register as an Organization** — skips it entirely |
| 🟠 | EU DSA trader status or removal in the EU | Both | Business address + phone, publicly shown. **Never a home address** |
| 🟠 | European Accessibility Act — law, not store policy ⚠️ | EU | VoiceOver/TalkBack, 200% type, 4.5:1, 48dp, accessibility statement |
| 🟠 | Apple 4.3(b) — "indistinguishable" apps rejected *and removed* | Apple | Household sharing + sitter access + interactive widgets + claim packs in v1; say so in review notes |

---

## Phase 0 — before any code

- [ ] Register a legal entity
- [ ] Apply for a **D-U-N-S number** (free, ~2 weeks lead time)
- [ ] Enrol as an **Organization** on Apple ($99/yr) and Google Play ($25 one-time)
- [ ] Complete developer identity verification on both platforms
- [ ] Trademark clearance on the final name — US (USPTO), EU (EUIPO), UK (IPO), CA
- [ ] Submit a Play Console policy question: does a pet-medication reminder qualify for `USE_EXACT_ALARM`? (Expected answer: no)

## Legal documents — live before submission

All HTTPS, non-PDF, globally reachable, naming the company and the app.

- [ ] Privacy Policy — what is collected, how, all uses, third-party sharing (incl. any AI provider), retention, deletion, how to revoke consent, contact info
- [ ] Terms of Use / EULA — objectionable-content prohibition, **16+ minimum age**, auto-renewal terms, disclaimer of veterinary advice
- [ ] Community Guidelines — defines objectionable content and behaviour
- [ ] **Public account-deletion page** — no login required, direct link, not a homepage
- [ ] Accessibility statement (EAA)
- [ ] Support email + support URL, on both listings and in-app

## Code — both platforms

**Paywall**

- [ ] Subscription name, duration, what you get
- [ ] Full billed price is the **most prominent** pricing element (annual total larger than the per-month equivalent)
- [ ] Explicit auto-renewal sentence
- [ ] Trial duration, **exact end date**, price after, how to cancel before charging
- [ ] Restore Purchases button
- [ ] Terms of Use + Privacy Policy links on the sheet
- [ ] Visible close/dismiss control
- [ ] CTA states the obligation to pay — "Subscribe — $29.99/year"
- [ ] Cancellation in Settings, ≤ 2 taps, deep-linked to the store's subscription centre
- [ ] No guilt screens, no fake X, no pre-ticked boxes, no benefit reduction after period one
- [ ] All price and term strings fully localised

**Accounts and data**

- [ ] In-app "Delete Account" — real deletion, not deactivation, not a mailto
- [ ] Household-owner deletion semantics implemented (transfer or dissolve)
- [ ] Revoke Sign in with Apple tokens via Apple's REST API on deletion
- [ ] TLS everywhere, including image and PDF transfer
- [ ] Data safety form ⟷ Nutrition Labels ⟷ privacy policy ⟷ actual behaviour — all four consistent

**UGC**

- [ ] ToS acceptance before first upload; log consent + version + timestamp
- [ ] Report on every photo, note, comment and log entry
- [ ] Report user + block/remove member
- [ ] Abuse inbox, 24-hour response commitment, actually staffed
- [ ] Server-side image moderation on publicly shareable assets (lost-pet posters)
- [ ] Invite tokens: scoped, expiring, single-use, revocable; sitter expiry enforced **server-side**
- [ ] Verified App Links / Universal Links — no custom URL schemes

**Health-adjacent**

- [ ] "Not a medical device / consult a vet" disclaimer on first run, in Settings, on every AI output, in every exported PDF footer
- [ ] Reminder-reliability disclaimer on the medication screen
- [ ] **No dosage calculator** (Apple 1.4.2)
- [ ] Copy audit: no "diagnose", "treat", "prescribe", "clinically proven", "vet-approved", "detects", "monitors your pet's health"

**Accessibility**

- [ ] VoiceOver / TalkBack labels on every control, paywall included
- [ ] Dynamic Type / font scaling to 200% with no clipping or loss of function
- [ ] Contrast ≥ 4.5:1; status never conveyed by colour alone
- [ ] Touch targets ≥ 44pt / 48dp
- [ ] Reduce Motion respected
- [ ] Exported PDFs tagged/accessible (a real engineering task — budget it)

---

## Google Play

**Console declarations — all mandatory**

- [ ] Data safety form + deletion URL + encryption-in-transit answer
- [ ] Health apps declaration
- [ ] Target audience → **adult age groups only**; do **not** opt into Designed for Families
- [ ] Content rating (IARC) — unrated apps are prohibited
- [ ] Ads declaration → no ads
- [ ] App access → demo credentials and step-by-step instructions for household sharing
- [ ] Privacy policy URL
- [ ] AI-generated content declaration for any AI-made listing asset

**Manifest — audit the MERGED manifest, not your own**

- [ ] `SCHEDULE_EXACT_ALARM` declared
- [ ] `USE_EXACT_ALARM` **NOT** declared — its presence blocks the upload
- [ ] `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_EXTERNAL_STORAGE` **removed** — KMP image libraries inject these transitively
- [ ] `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` not requested
- [ ] `READ_CONTACTS` not requested — share-sheet invites only
- [ ] No location permissions of any kind
- [ ] `POST_NOTIFICATIONS` requested contextually with rationale; separate channels (Medication, Feeding, Vaccinations, Household activity)

**Technical**

- [ ] Target **API 36**
- [ ] **16 KB page size** — audit every native `.so` (SQLite, PDF, image codecs, crash SDK). Verify with `check_elf_alignment.sh`
- [ ] **Play Billing Library 8.0.0+**
- [ ] AAB + Play App Signing
- [ ] Prominent disclosure + affirmative consent before the first cloud upload
- [ ] Title ≤ 30 chars; 2–8 phone screenshots; no price/promo words, emojis, ALL CAPS, "#1", or CTAs in title/icon/screenshots

---

## Apple

**App Store Connect**

- [ ] Privacy Nutrition Labels — pet records as **User Content → Other User Content**, not Health (document the reasoning)
- [ ] Privacy Policy URL; User Privacy Choices URL → the deletion page
- [ ] Age rating questionnaire incl. medical/wellness **and** social-media questions (required from Sept 2026). Expect 13+. **Not** the Kids Category
- [ ] EU DSA trader status verified
- [ ] License Agreement — standard Apple EULA linked at the end of the description, or upload a custom one
- [ ] Small Business Program enrolment (15%)
- [ ] Family Sharing enabled on the subscription ⚠️ **irreversible**
- [ ] Introductory Offer configured for the free trial

**App Review Information — reviewers get stuck on household sharing without this**

- [ ] Working demo account (no email OTP the reviewer cannot receive)
- [ ] **A second demo account already a member of the first account's household**
- [ ] Pre-seeded household: 2 pets, photos, feeding logs, a vaccination record, an active reminder
- [ ] A pre-generated, still-valid invite link pasted into the notes
- [ ] Numbered walkthrough script
- [ ] Note where the paywall is, that IAP is sandbox-testable, that Restore is on the paywall
- [ ] Note that no location or contacts data is used
- [ ] A paragraph on what makes this different from existing pet trackers (4.3(b) defence)

**Build**

- [ ] Xcode 26 / iOS 26 SDK; deliberate Liquid Glass decision
- [ ] `PrivacyInfo.xcprivacy` with `NSPrivacyCollectedDataTypes` matching the Nutrition Labels, and `NSPrivacyAccessedAPITypes` reason codes:
  - `NSPrivacyAccessedAPICategoryUserDefaults` → **CA92.1**
  - `NSPrivacyAccessedAPICategoryFileTimestamp` → C617.1 / 0A2A.1
  - `NSPrivacyAccessedAPICategoryDiskSpace` → E174.1 / 85F4.1
  - `NSPrivacyAccessedAPICategorySystemBootTime` → 35F9.1
- [ ] `NSPrivacyTracking` = false, `NSPrivacyTrackingDomains` empty
- [ ] Verify the manifest survives KMP → SPM/CocoaPods packaging; **generate the Privacy Report and diff it against the labels**
- [ ] Every third-party SDK audited for a bundled privacy manifest **and signature**
- [ ] Sign in with Apple implemented (mandatory because we offer Google)
- [ ] Specific purpose strings; use `PHPickerViewController` so `NSPhotoLibraryUsageDescription` can be omitted; delete every unused usage-description key
- [ ] Rolling 64-notification window; server push for long-horizon reminders
- [ ] No ads in widgets/extensions/watchOS; Live Activities strictly functional

---

## Verify before relying on these ⚠️

| Claim | How to resolve |
|---|---|
| Medication apps do not qualify for `USE_EXACT_ALARM` | Play Console policy question. Worst case, ship `SCHEDULE_EXACT_ALARM` anyway — always safe |
| Play requires ≤ 2-tap in-app cancellation | Build it regardless; zero cost |
| Health declaration deadline (Aug 2024 vs Jul 2026) | Just complete it — it blocks publishing either way |
| Whether **pet** health data counts as "Health" | Declare as User Content / Files & docs, document reasoning, ship the disclaimer as a hedge |
| Play AI policy in-app reporting requirement | Read the policy in Console before shipping any AI feature |
| iOS 64-notification cap | Architect around it regardless |
| EAA microenterprise exemption (<10 staff, ≤ €2M) | **Get EU legal advice before EU launch** — transpositions vary by member state |
| Apple 4.3(b) applicability | Cannot be resolved in advance. Differentiate aggressively |
