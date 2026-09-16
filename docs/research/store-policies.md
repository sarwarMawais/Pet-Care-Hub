# App Store Compliance Report — "Pet Care Hub"
**Scope:** Google Play + Apple App Store · Kotlin Multiplatform · Launch 2026 (US/CA/UK/EU)
**Research date:** 15 September 2026
**Confidence key:** ✅ = verified against official Apple/Google doc · ⚠️ = from secondary source or ambiguous, verify before relying on it

---

## 0. Executive summary — the things most likely to actually bite you

| # | Risk | Store | Severity |
|---|------|-------|----------|
| 1 | **Medication reminders + Android exact alarms.** You do **not** qualify for `USE_EXACT_ALARM`; you must use `SCHEDULE_EXACT_ALARM` with a user-granted, deniable special permission. Design for the denied case or your core value prop silently breaks. | Play | 🔴 Critical |
| 2 | **Paywall disclosure (Apple 3.1.2).** Most common rejection for subscription apps. Needs price, duration, auto-renew language, Restore, ToU + Privacy links **on the paywall itself**. | Apple | 🔴 Critical |
| 3 | **Google Play Health apps declaration** is mandatory for *every* app on Play. You must answer it — and answering it wrong (either way) is a policy problem. Pet/vet health is not addressed by the policy text. | Play | 🟠 High |
| 4 | **Household sharing = UGC.** Apple 1.2 has no private-group exemption; Play's UGC policy explicitly names *closed groups* and requires in-app reporting. | Both | 🟠 High |
| 5 | **12-tester / 14-day closed test** if you use a personal Play account created after 13 Nov 2023. Budget ~3–4 weeks before you can even go to production. | Play | 🟠 High |
| 6 | **DSA trader status** must be declared on both stores or you're removed/blocked in the EU. | Both | 🟠 High |
| 7 | **European Accessibility Act** applies to consumer e-commerce apps in the EU since 28 Jun 2025 — this is a *law*, not a store policy, and neither store will warn you. | EU law | 🟠 High |
| 8 | **Apple 4.3(b) (new, June 2026)** — Apple now rejects/removes apps "indistinguishable" from existing ones in well-established categories. Pet trackers are a crowded category. | Apple | 🟠 High |

---

# 1. Subscriptions & Billing

## 1.1 Mandatory store billing for digital goods

**Rule (Apple 3.1.1):** Unlocking features or functionality inside the app — subscriptions, premium content, full-version unlock — **must** use In-App Purchase. You may not use license keys, QR codes, or your own payment mechanism. ✅

**Rule (Play Payments):** Same principle — digital goods/services consumed inside the app must use Google Play's billing system, with the 2026 exceptions below. ✅

**What applies to you:** "Unlimited pets on one price," cloud sync, PDF export, AI features = digital functionality. **All of it must go through IAP/Play Billing.** Physical goods (e.g. if you ever sell printed lost-pet posters, or vet services) must *not* use IAP (Apple 3.1.3(e)).

**Note on 3.1.3(d) person-to-person:** If you later add *real-time 1:1 paid vet consults*, that's explicitly exempt from IAP under Apple 3.1.3(d) ("medical consultations" is a named example). One-to-few/one-to-many must use IAP. ✅

Sources: [Apple App Review Guidelines 3.1.1 / 3.1.3](https://developer.apple.com/app-store/review/guidelines/) · [Play Payments policy](https://support.google.com/googleplay/android-developer/answer/9858738?hl=en)

---

## 1.2 Apple 3.1.2 — subscription specifics (the rejection magnet)

**Rules (verbatim-derived):** ✅
- Subscription period must be **≥ 7 days** and work **across all the user's devices**.
- Must provide **ongoing value** — "SAAS," "cloud support," and "consistent, substantive updates" are explicitly listed as acceptable. Cloud sync + reminders + household sharing comfortably qualifies.
- 3.1.2(b): seamless upgrade/downgrade; users must not be able to accidentally hold two variants of the same thing.
- 3.1.2(c): "Before asking a customer to subscribe, you should clearly describe what the user will get for the price… clearly communicate the requirements described in **Schedule 2** of the Apple Developer Program License Agreement."
- Explicit anti-scam clause: apps that "trick users into purchasing a subscription under false pretenses or engage in bait-and-switch" are removed, and the developer account may be terminated.

**What must be on the paywall screen** (per Apple's official Auto-renewable Subscriptions page): ✅
1. Subscription **name** and **duration**, with a clear description of what's provided.
2. **Full renewal price** in localized currency — and **the amount to be billed must be the most prominent pricing element**. For your annual plan, "€39.99/year" must be visually dominant; "just €3.33/month" may only appear in subordinate position and smaller size.
3. **A way for current subscribers to sign in or restore purchases** → the **Restore Purchases** button is effectively mandatory on the paywall. ✅
4. Free trial: **how long the trial lasts and the price billed once it ends**. ✅
5. Functional links to **Terms of Use (EULA)** and **Privacy Policy**. ⚠️ (The "in the binary" checklist wording is widely cited from Apple's older review checklist; the current official subscriptions page states "Your app and App Store metadata must include links to your Terms of Use and Privacy Policy." Treat as required in both places.)

**Where the ToU/EULA link goes:** ⚠️
- If using Apple's **standard EULA**: put `https://www.apple.com/legal/internet-services/itunes/dev/stdeula/` in your **App Store description** (end of text) *and* on the paywall.
- If using a **custom EULA**: upload it in App Store Connect → App Information → License Agreement, and link the same in-app.
- Privacy Policy URL field in App Store Connect is **mandatory** (5.1.1(i)) and must also be reachable **inside** the app. ✅

**Free trial mechanics (Apple):** Auto-renewable subscription apps offer trials via **Introductory Offers** configured in App Store Connect. One introductory offer per subscription group per customer. ✅ (Do *not* use the "XX-day Trial" Non-Consumable Price-Tier-0 pattern — that's only for *non-subscription* apps.)

**Family Sharing:** ✅ Optional but a strong fit for a *household* app. Enable per-subscription in App Store Connect; shares with up to 5 family members. **Irreversible once enabled.** Check `Transaction.ownershipType` to distinguish purchaser from family member. Caveat: Apple Family Sharing ≠ your app's household groups — an Apple "family" is Apple-account-based. You still need your own household/invite model for sitters and walkers who are not in the Apple family. Recommend enabling Family Sharing anyway; it's a retention win and Apple recommends naming it in the subscription display name.

Sources: [Apple 3.1.2](https://developer.apple.com/app-store/review/guidelines/) · [Auto-renewable Subscriptions](https://developer.apple.com/app-store/subscriptions/) · [App Store Connect — offer auto-renewable subscriptions](https://developer.apple.com/help/app-store-connect/manage-subscriptions/offer-auto-renewable-subscriptions/) · [Enable Family Sharing for your subscriptions](https://developer.apple.com/news/?id=ksfkdwpr)

---

## 1.3 Google Play Subscriptions policy

**Rules:** ✅
- You must **"clearly and explicitly disclose"**: offer terms, cost, billing-cycle frequency, **automatic renewal terms**, whether a subscription is required to use the app, and any other material information. *"Users should not have to perform any additional action to review the information."*
- Subscriptions must deliver **sustained or recurring value** — a one-time benefit sold as a subscription is a violation (use a one-time in-app product instead).
- **Free trials / intro offers**: before enrolment you must state the trial duration, when it converts to paid, the exact paid price, and **how to cancel before charges begin**. All of this must be **fully localized** — incomplete localization of price and terms is a named violation.
- **SKU naming**: a SKU named "Free Trial" or "Try Premium — 3 days for free" for a product that auto-charges is an explicit violation.
- **Cancellation**: you must disclose how to cancel, by **linking to Google Play's Subscription Center** and/or providing direct in-app cancellation access.
- Named violations also include: showing a misleading monthly breakdown of a longer billing cycle; subscription prompts with **no dismiss button**; reducing benefits after the initial period.

⚠️ **Uncertain:** Several 2026 secondary sources claim Play now mandates in-app cancellation "in no more than two taps" and a late-2025 enforcement sweep against dark patterns. I could **not** confirm the two-tap rule in official Google documentation. Treat it as best practice, not a citable rule — but building a 2-tap cancel path costs you nothing and removes the risk entirely.

**Deep link to use:** `https://play.google.com/store/account/subscriptions?sku=<productId>&package=<packageName>` ✅

Sources: [Play Subscriptions policy](https://support.google.com/googleplay/android-developer/answer/9900533?hl=en) · [Manage subscriptions (Play Billing)](https://developer.android.com/google/play/billing/manage-purchases) · ⚠️ [Play Console announcements index](https://support.google.com/googleplay/android-developer/announcements/13412212?hl=en)

---

## 1.4 Commission tiers — Apple Small Business Program & Play

**Apple Small Business Program:** ✅ 15% instead of 30%. Eligibility: ≤ **$1M USD in proceeds** (net of Apple's commission, not gross) in the prior calendar year, across **all Associated Developer Accounts**. New developers qualify automatically. You must enroll — it is **not** automatic. Re-qualification possible the year after dropping below the threshold.

**Apple alternative:** Even outside SBP, **auto-renewable subscriptions drop to 15% after a subscriber's first year** of paid service.

**Google Play (2026 restructure — effective 30 June 2026):** ⚠️/✅
- Fees split into a **service fee** and a **billing fee**.
- Service fee starts at **10%** on the first $1M USD annual earnings; **all auto-renewing subscriptions are at 10%**.
- A separate **5% billing fee** applies in the **US, UK, and EEA** when using Google Play's billing system. → effective **~15%** for your subscriptions in those markets.
- **No billing fee** for alternative billing systems or external web links.
- Legacy: the 15%-on-first-$1M tier (since Jul 2021) required enrolment via Play Console.

Sources: [App Store Small Business Program](https://developer.apple.com/app-store/small-business-program/) · [Play: Expanded billing choice and lower fees](https://android-developers.googleblog.com/2026/06/play-expanded-billing.html) · [Understanding Google Play's Service Fee](https://support.google.com/googleplay/android-developer/answer/11131145?hl=en) · [Changes to Google Play's service fee in 2021](https://support.google.com/googleplay/android-developer/answer/10632485?hl=en)

---

## 1.5 EU DMA / alternative billing — should you bother?

**Apple (EU):** ✅ You *may* apply for the **StoreKit External Purchase Link Entitlement** to link out to your own web purchase flow. Under the EU terms updated **18 August 2026** (primary changes effective **1 October 2026**), Apple moved from the Core Technology Fee to a **Core Technology Commission (CTC)** plus an **initial acquisition fee** and **store services fee**. In the **US storefront** (post-Epic), external links/buttons/CTAs are permitted without any entitlement. ✅

**Google (EU/UK/US):** ✅ Alternative billing + external web links are available and **avoid the 5% billing fee**; a custom choice screen is permitted per Google's UX guidelines.

### 🔴 Recommendation: **Do not bother at launch.**
Rationale: for a sub-$1M indie subscription app, the entitlement paperwork, separate merchant-of-record setup, EU VAT/MOSS registration, refund handling, chargeback risk, and the **elevated rejection/compliance surface** dwarf the few percentage points saved — and Apple's EU fee stack (acquisition fee + store services fee + CTC) means the net saving is often near zero or negative at your scale. Revisit only if (a) you exceed ~$500k ARR, or (b) you have a web-first funnel already converting.

⚠️ Fee percentages in the EU program are in flux through late 2026; confirm current numbers at the Apple EU page before modelling.

Sources: [Changes for apps in the EU (Apple)](https://developer.apple.com/support/apps-in-the-eu/) · [Updates for apps in the EU](https://developer.apple.com/news/?id=awedznci) · [Apple 3.1.1(a) / 3.1.3](https://developer.apple.com/app-store/review/guidelines/) · [Play expanded billing](https://android-developers.googleblog.com/2026/06/play-expanded-billing.html)

---

## 1.6 Consumer-law overlay (not store policy, but store-enforced in effect)

- **US FTC "Click-to-Cancel" (Negative Option Rule)** was **vacated in its entirety by the Eighth Circuit on 8 July 2025** on procedural grounds. ✅ The FTC has moved to revive it. **Do not treat it as dead** — and a patchwork of **state ARLs** still imposes near-identical duties.
- **California ARL** (amendments effective **1 July 2025**): express affirmative consent to auto-renewal terms; **retain consent records ≥ 3 years** (or 1 year post-termination, whichever is longer); for terms ≥ 1 year that auto-renew, send a renewal reminder **15–45 days before renewal**. ⚠️ Note: when Apple/Google are merchant of record they handle *some* of this, but the affirmative-consent and disclosure obligations at your paywall are yours.
- **EU Consumer Rights Directive**: pre-contractual information + the "order with obligation to pay" button labelling. Your paywall CTA should read **"Subscribe — €39.99/year"**, not "Continue" or "Start."

Sources: [Eighth Circuit vacates FTC Click-to-Cancel (Cooley)](https://www.cooley.com/news/insight/2025/2025-07-11-click-to-cancel-just-got-cancelled-eighth-circuit-vacates-entirety-of-ftcs-negative-option-rule) · [FTC moves to revive (Crowell)](https://www.crowell.com/en/insights/client-alerts/clicking-all-the-right-boxes-ftc-moves-to-revive-click-to-cancel-rule-following-eighth-circuit-vacatur) · [California ARL amendments (Cooley)](https://www.cooley.com/news/insight/2025/2025-06-04-california-automatic-renewal-law-amendments-take-effect-on-july-1-2025) · [CA AG consumer alert](https://oag.ca.gov/news/press-releases/attorney-general-bonta-issues-consumer-alert-california%E2%80%99s-automatic-renewal-law)

---

# 2. Accounts & Data

## 2.1 Account deletion

**Apple 5.1.1(v):** ✅ *"If your app supports account creation, you must also offer account deletion within the app."* Also: *"If your app doesn't include significant account-based features, let people use it without a login."*

Concrete requirements: ✅/⚠️
- A **visible UI control** ("Delete Account") inside the app, typically in account settings. A `mailto:` link, a "Contact us" page, or a generic website redirect **does not pass**. ⚠️ (widely reported rejection pattern; consistent with Apple's own support page)
- It must delete **the full account record plus associated personal data** — deactivation/suspension is not sufficient.
- If deletion is completed on the web, you must **deep-link directly to that page**, not to a homepage. ✅
- **If you offer Sign in with Apple, you must revoke the user's token via Apple's REST API on deletion.** ⚠️ (Apple's account-deletion support page; strongly enforced in practice)

> **Design note for your app:** what happens when the account being deleted is the *household owner* and other members (sitters) still have data in the shared pet record? You need a defined path — transfer ownership, or delete the household. Get this right before submission; reviewers do test it.

**Google Play:** ✅ If the app allows in-app account creation, you must provide **both**:
1. An **in-app path** to delete the app account and associated data — "intuitive and prominent," e.g. in account settings.
2. A **publicly accessible web link** where users can request account + data deletion. Must be reachable **without login**, HTTPS, and link **directly** to the deletion page. ⚠️ (the "without login / direct page" specifics come from secondary guidance; the official page requires the link be "functional, relevant, and prominently feature the deletion pathway")

Both must be declared in the **Data safety form** (Play Console → App content). Data retained for security/fraud/regulatory reasons may be kept but **must be disclosed in your privacy policy**. Third-party processors must be instructed to delete too. Non-mobile form factors (Wear OS, TV) only need the web option. Full enforcement since **April 2024**.

Sources: [Apple 5.1.1(v)](https://developer.apple.com/app-store/review/guidelines/) · [Offering account deletion in your app (Apple)](https://developer.apple.com/support/offering-account-deletion-in-your-app) · [Play account deletion requirements](https://support.google.com/googleplay/android-developer/answer/13327111?hl=en)

---

## 2.2 Sign in with Apple (Apple 4.8)

**Rule:** ✅ If you use a **third-party/social login** (Google Sign-In counts) to set up or authenticate the user's **primary account**, you must **also** offer an equivalent login service that:
- limits data collection to **name and email address**;
- lets users **keep their email address private**;
- does **not** collect in-app interactions for advertising without consent.

**Exceptions** (none of which apply to you): ✅ your own account system exclusively; alternative app marketplaces; education/enterprise apps requiring an existing org account; government/industry citizen ID; client apps for a specific third-party service.

**⚠️ Important nuance:** Guideline 4.8 as currently written does not literally name "Sign in with Apple" — it describes *capabilities*. In practice, Sign in with Apple is the only service that reliably satisfies all three criteria, and App Review treats it as the expected implementation.

**Your decision:** You plan email + Google + Apple. Since you're offering Google Sign-In, **Sign in with Apple is required on iOS**. ✅ (Also note: offering plain email/password alone does *not* exempt you — the guideline triggers on the presence of the third-party service.)

Source: [Apple 4.8](https://developer.apple.com/app-store/review/guidelines/)

---

## 2.3 Apple Privacy Nutrition Labels — your likely declarations

Based on Apple's official data-type taxonomy: ✅

| Category → Type | Collected? | Linked to user? | Purpose |
|---|---|---|---|
| Contact Info → **Name**, **Email Address** | Yes | Linked | App Functionality, Account Management |
| Contact Info → **Physical Address** | Only if you store home address for lost-pet posters | Linked | App Functionality |
| **User Content** → Photos or Videos | Yes (pet photos, vet invoices) | Linked | App Functionality |
| **User Content** → Other User Content | Yes (symptom notes, med logs, PDFs) | Linked | App Functionality |
| **Identifiers** → User ID | Yes | Linked | App Functionality |
| **Purchases** → Purchase History | Yes (subscription state) | Linked | App Functionality |
| **Usage Data** → Product Interaction | If you add analytics | Linked or Not Linked | Analytics |
| **Diagnostics** → Crash Data, Performance Data | If you add Crashlytics/Sentry | Usually Not Linked | App Functionality / Analytics |

### 🔑 The "Health" question
Apple's **Health & Fitness → Health** type is defined as: *"Health and medical data from the Clinical Health Records API, HealthKit API, Movement Disorder API, health-related human subject research, or **user-provided health/medical data**."* ✅

⚠️ **Genuinely ambiguous whether *animal* health data falls here.** The definition doesn't say "human," but the entire surrounding framework (HealthKit, 5.1.3, human subject research) is about people. **Recommendation:** declare pet vaccination/weight/medication records under **User Content → Other User Content**, *not* Health. Rationale: (a) it's not human health data, (b) declaring "Health" triggers additional scrutiny and 5.1.3 expectations, (c) the data genuinely is user-authored content. Document this reasoning internally in case a reviewer queries it. If you ever add *owner* health data (e.g. allergy info for a human), switch to Health.

**Do not forget:** ✅
- You are **responsible for disclosing all data collected by third-party SDKs** you integrate.
- Data processed **only on-device and never sent off-device is not "collected"** and needs no disclosure — relevant if you do on-device AI.
- "Collect" = transmitting off-device in a way that lets you access it **longer than necessary to service the request in real time**. An IP address sent but not retained isn't collection.
- **Privacy Policy URL** is required. **User Privacy Choices URL** is optional — but since Play requires a public deletion URL anyway, reuse the same page here. ✅

Source: [Apple App Privacy Details](https://developer.apple.com/app-store/app-privacy-details/)

---

## 2.4 Google Play Data safety form — your likely declarations

Mandatory for all apps (except system services and private apps). ✅

**Data types you'll declare (collected + likely shared-with-service-providers-only):**
- **Personal info**: Name, Email address, (Address — only if stored)
- **Photos and videos**
- **Files and docs** (vet invoices, PDFs)
- **Health and fitness** — ⚠️ same ambiguity as Apple. Play's category is "Health and fitness → Health info: information about an individual's health." *"An individual"* reads as a person. **Recommendation: declare under "Files and docs" / "Personal info → Other info", not Health info** — but see §4.2, because the *Health apps declaration* is a separate form with a separate answer.
- **Financial info → Purchase history**
- **App activity**, **App info and performance**, **Device or other IDs** (if analytics/crash SDKs)

**Security practices you must answer:** ✅
- Is data **encrypted in transit**? → Yes (must be true; use TLS everywhere including image/PDF uploads).
- Can users **request data deletion**? → Yes, plus the deletion URL.

**Critical:** the Data safety form must be **consistent with your privacy policy** and must cover **all app versions globally**, including data collected by **third-party libraries and SDKs**. Mismatches between the form, the policy, and observed app behaviour are a top enforcement trigger.

Sources: [Play Data safety](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en) · [Play User Data policy](https://support.google.com/googleplay/android-developer/answer/10144311?hl=en)

---

## 2.5 App Tracking Transparency

**Rule:** ✅ Apple's definition — *"linking user or device data collected from your app with user or device data collected from other companies' apps, websites, or offline properties for targeted advertising or advertising measurement purposes,"* or sharing with a **data broker**.

**Does it apply to you?**
- **Pure organic launch, no ad SDKs, no attribution SDK** → **ATT prompt not required**, and you should **not** show it (an unnecessary prompt is itself a bad signal).
- **The moment you add** Meta Ads SDK, TikTok, AppsFlyer/Adjust/Branch in attribution mode, or Google Ads conversion tracking with IDFA → **ATT required**, and the prompt must fire *before* any tracking.
- **Firebase Analytics / Crashlytics alone** → generally not tracking (no cross-company linking for ads).

⚠️ Apple explicitly warns that a third-party SDK can make you a tracker *even if you personally don't use the data for ads* (e.g. a login SDK that repurposes data). **Ask every SDK vendor in writing.**

**Also:** Guideline 5.1.2(i) — *"Your app may not require users to enable system functionalities (e.g. push notifications, location services, tracking) in order to access functionality, content, use the app, or receive monetary or other compensation."* ✅ → **Never gate the free tier or a trial behind granting notifications or ATT.**

Sources: [Apple 5.1.2](https://developer.apple.com/app-store/review/guidelines/) · [App Privacy Details — Tracking definition](https://developer.apple.com/app-store/app-privacy-details/) · [If an app asks to track your activity](https://support.apple.com/en-us/102420)

---

## 2.6 GDPR / UK GDPR

**Analysis (legal, not store policy — get counsel for EU launch):**

- **Pet health data is *not* GDPR "special category" data.** Art. 9 covers data concerning the health of a **natural person**. Your vaccination records, weight charts and symptom notes about *animals* are ordinary personal data (they relate to the owner as data subject, being data about their property/household).
- **But** several things *are* ordinary personal data and need a lawful basis, retention policy, and DSAR handling: owner name/email, **home address** (if you store it for lost-pet posters), **household membership graph** (who lives with whom, who walks the dog), **photos** (which may contain identifiable people in the background), and **sitter/walker identities** (third parties who may not have a direct relationship with you).
- **Invited guests are data subjects too.** When a user invites a dog walker by link, you collect that walker's email/name. You need a lawful basis (legitimate interest, likely) and must serve them privacy information at the point of joining.
- **Children:** if a teenager in the household is invited, you're collecting a minor's data. The EU age of digital consent varies 13–16 by member state. **Mitigation:** restrict invite acceptance to 16+ in your ToS and don't ask for DOB.
- **Time-limited guest access** is a genuine GDPR-positive: it's storage limitation and data minimisation by design. Say so in your privacy policy.
- **Transfers:** if your backend is US-hosted (Firebase/Supabase/AWS us-east), you need an Art. 46 transfer mechanism (SCCs / EU-US DPF). Prefer an **EU region** for EU users if feasible.
- **Apple 5.1.1(ii)** explicitly says: apps relying on GDPR **legitimate interest** without consent *"must comply with all terms of that law."* ✅

**Practical deliverables:** privacy policy (public URL, HTTPS, non-PDF, names your app and company, has contact info, covers retention + deletion + revoking consent), a DPA with every processor, a records-of-processing doc, and a DSAR/deletion inbox.

Sources: [Apple 5.1.1(i)–(ii)](https://developer.apple.com/app-store/review/guidelines/) · [Play User Data policy](https://support.google.com/googleplay/android-developer/answer/10144311?hl=en)

---

# 3. Permissions

## 3.1 🔴 Android exact alarms — the single biggest technical/policy risk

**The rule:** ✅
- **`USE_EXACT_ALARM`** is a *normal* permission, auto-granted at install, **but restricted by Play policy to apps whose "core, user-facing functionality requires precisely-timed actions" — specifically alarm/timer apps and calendar apps.** Google states: *"Apps will not be able to publish a version of their app with this permission in the manifest unless they qualify based on the policy language."*
- **A medication/pet-care reminder app does not qualify.** ⚠️ (No official doc names medication trackers explicitly; this is the consistent reading of the policy language plus developer-community experience. **Flagged as the highest-value item to verify** — consider a pre-submission policy question to Google.)
- **`SCHEDULE_EXACT_ALARM`** is what you must use. On Android 14+, it is **denied by default** for newly installed apps that target API 33+ and aren't calendar/alarm apps.

**What you must build:** 🔴
```kotlin
if (alarmManager.canScheduleExactAlarms()) {
    alarmManager.setExactAndAllowWhileIdle(...)   // or setAlarmClock(...)
} else {
    // Explain WHY, then:
    startActivity(Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        .putExtra("package", context.packageName))
}
```
1. Declare **only** `SCHEDULE_EXACT_ALARM` (not `USE_EXACT_ALARM`) — declaring the latter will get the release blocked at upload.
2. **Always check `canScheduleExactAlarms()` before every schedule**, and re-check on `ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED` (the OS can revoke it).
3. Build an **honest, working fallback** using inexact alarms / `WorkManager` for users who deny. Medication reminders that fire ±15 min are still useful; medication reminders that silently never fire are a 1-star review and a refund.
4. **Onboarding UX:** a dedicated "Reliable reminders" screen explaining *why* exact alarms matter for medication, with a single CTA into the system settings. Do not nag.
5. Consider `setAlarmClock()` for the truly critical ones — it's the strongest Doze exemption and shows in the system alarm UI.

**Do NOT request `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`.** ⚠️ It's a restricted permission; acceptable use cases are narrow (real-time fitness/location tracking during a workout, turn-by-turn navigation, VoIP). "Reminders might be delayed" is not a qualifying justification and requesting it risks suspension.

Sources: [Schedule exact alarms are denied by default (Android 14)](https://developer.android.com/about/versions/14/changes/schedule-exact-alarms) · [Schedule alarms](https://developer.android.com/develop/background-work/services/alarms) · [Play: Permissions and APIs that Access Sensitive Information](https://support.google.com/googleplay/android-developer/answer/16558241?hl=en) · [Optimize for Doze and App Standby](https://developer.android.com/training/monitoring-device-state/doze-standby)

---

## 3.2 Android photos & video

**Rule:** ✅ Apps targeting Android 13+ (API 33+) may request `READ_MEDIA_IMAGES` / `READ_MEDIA_VIDEO` **only if system pickers (Android Photo Picker) are not sufficient for core functionality.** A Play Console declaration explaining why the picker is insufficient is required if you request them. Full enforcement since **28 May 2025** — non-compliant apps are subject to removal.

**Verdict for you:** 🟢 **You do not need `READ_MEDIA_*` at all.** Your use cases — pick a pet photo, attach a vet invoice — are exactly what the **Android Photo Picker** (`PickVisualMedia` / `ACTION_PICK_IMAGES`) and the **Storage Access Framework** (`ACTION_OPEN_DOCUMENT` for PDFs) are for. Neither requires *any* runtime permission.

**Action:** strip `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_EXTERNAL_STORAGE` from **every** version code in the submission, including from transitive AAR manifests (run `./gradlew :composeApp:processReleaseManifest` and inspect the merged manifest — KMP/Compose image libs frequently inject these).

Sources: [Play Photo & Video Permissions](https://support.google.com/googleplay/android-developer/answer/15800983?hl=en) · [Restricted permissions with minimum-scope alternatives](https://support.google.com/googleplay/android-developer/answer/14115180?hl=en) · [Android Photo Picker (Android Developers Blog)](https://android-developers.googleblog.com/2025/04/google-play-empowering-developers-to-build-user-trust-through-privacy.html)

---

## 3.3 Android camera

`CAMERA` is a standard dangerous runtime permission and is fine for a "take a photo of your pet / snap the vet invoice" flow. ✅

**Better:** where you only need a single image, use `ACTION_IMAGE_CAPTURE` via an intent to the system camera — **no permission required at all**. Only declare `CAMERA` if you build an in-app capture UI (e.g. document-edge detection for invoices). Play's data-minimisation stance favours the intent approach.

⚠️ Note: if `CAMERA` appears anywhere in your merged manifest, Play may show it in the listing and the Data safety form must account for the resulting photos.

---

## 3.4 Android notifications (Android 13+)

**Rule:** ✅ `POST_NOTIFICATIONS` is a runtime permission on API 33+. Notifications are **off by default** on fresh installs. If the user taps "Don't allow" **twice**, the OS stops prompting.

**What to do:**
- **Don't ask on first launch.** Ask at the moment the user creates their first reminder — "Turn on notifications so we can remind you about Luna's flea treatment." Contextual asks convert at 2–3× cold asks.
- Use `shouldShowRequestPermissionRationale()` and a pre-prompt.
- If denied, degrade gracefully and surface a persistent (dismissible) in-app banner with a deep link to `Settings.ACTION_APP_NOTIFICATION_SETTINGS`.
- Use **separate notification channels** (Medication, Feeding, Vaccinations, Household activity, Marketing) so users can mute the noisy ones instead of killing all notifications. This is both good UX and a defence against "spammy notifications" complaints.

Sources: [Notification runtime permission](https://developer.android.com/develop/ui/compose/notifications/notification-permission) · [Permission for opt-in notifications (AOSP)](https://source.android.com/docs/core/display/notification-perm)

---

## 3.5 Android — contacts & invites (new 2026 policy)

⚠️ **Heads-up:** Google announced a new **Contacts Permissions policy** on **15 April 2026**, effective **27 January 2027**: apps must use the **Android Contact Picker** instead of requesting broad contacts access.

**Impact on you:** if you ever add "invite from contacts" to the household-invite flow, use the **Contact Picker**, never `READ_CONTACTS`. Best of all: **don't touch contacts at all** — use a share-sheet invite link. That's zero permissions and zero policy surface.

Also announced 15 Apr 2026: **Foreground Services policy removed geofencing as an approved use case** (use the Geofence API); **Location Permissions policy** now recommends the *location button* as minimum scope (effective 27 Jan 2027). Neither affects you since you're not using location — **keep it that way**.

Sources: [Play policy announcement: April 15, 2026](https://support.google.com/googleplay/android-developer/answer/16926792?hl=en) · [Permissions and APIs that Access Sensitive Information](https://support.google.com/googleplay/android-developer/answer/16558241?hl=en)

---

## 3.6 Android — prominent disclosure & consent

**Rule:** ✅ Where access/collection/use/sharing of personal or sensitive data **may not be within the user's reasonable expectation**, you need an **in-app prominent disclosure** that:
- appears **within the app**, in normal usage, without requiring menu navigation;
- **describes the data** being accessed and **how it will be used/shared**;
- requires **affirmative user action** (a tap on "Accept"/"Continue"; not a dismissal or a back-press);
- appears **before** the runtime permission request.

**Where you need it:** before the first photo upload/cloud sync (data leaves the device), and before sharing anything to an external party (vet/groomer link, insurance pack). *"Photos you add are uploaded to Pet Care Hub's servers so they sync across your household devices."*

Sources: [Best practices for prominent disclosure and consent](https://support.google.com/googleplay/android-developer/answer/11150561?hl=en) · [Play User Data policy](https://support.google.com/googleplay/android-developer/answer/10144311?hl=en)

---

## 3.7 iOS purpose strings

**Rule:** ✅ Apple 5.1.1(ii): *"Ensure your purpose strings clearly and completely describe your use of the data."* 5.1.1(iii): *"Where possible, use the out-of-process picker or a share sheet rather than requesting full access to protected resources like Photos or Contacts."*

**Generic strings are an automatic rejection.** ⚠️ "This app requires photo library access to function properly" is a documented rejection trigger.

**Write these:**

| Key | Needed? | Suggested string |
|---|---|---|
| `NSCameraUsageDescription` | Yes | "Pet Care Hub uses the camera so you can take photos of your pets and capture vet invoices or documents to keep with their records." |
| `NSPhotoLibraryUsageDescription` | **Probably not** | Use `PHPickerViewController` — it requires **no permission and no purpose string**. Only add if you need full-library access (you don't). |
| `NSPhotoLibraryAddUsageDescription` | Only if you save generated lost-pet posters to the library | "Pet Care Hub saves the lost-pet poster you created to your photo library so you can print or share it." |
| `NSUserTrackingUsageDescription` | Only if you add ad/attribution SDKs | See §2.5 |
| `NSFaceIDUsageDescription` | Only if you biometric-lock the app | "Use Face ID to unlock Pet Care Hub and keep your pets' records private." |

**Do not add** location, contacts, microphone, health, calendar, or Bluetooth strings. Any unused purpose string in Info.plist invites a reviewer question about why you declared it.

Sources: [Apple 5.1.1](https://developer.apple.com/app-store/review/guidelines/) · [Requesting Permission (HIG)](https://developer.apple.com/design/human-interface-guidelines/privacy)

---

## 3.8 iOS notifications & background execution

- **Ask contextually**, same as Android. iOS gives you exactly one system prompt per install — burn it on a cold launch and you've lost the user forever. Use a pre-prompt, then `requestAuthorization`.
- Consider **provisional authorization** (`.provisional`) for quiet delivery without a prompt, then upgrade — good fit for low-stakes "fed 3h ago" nudges.
- 🔴 **Hard platform limit: iOS keeps only the 64 soonest-firing pending local notification requests per app.** ⚠️ (widely documented; not in a single crisp Apple doc, but consistently reported and observable) Anything beyond that is silently discarded.
  - **Mitigation:** maintain a rolling window. Schedule only the next N reminders; reschedule on every app foreground, on BGAppRefresh, and on silent push. For truly long-horizon items (annual vaccinations, quarterly parasite treatment) use **server-side push** rather than local notifications — this is the correct architecture for a cloud-synced app anyway, and it also solves cross-device dedup for households.
- **No `UIBackgroundModes` abuse.** Declaring `fetch`/`processing` you don't genuinely use is a rejection risk; `location` when you have no location feature is a guaranteed one.
- **Apple 5.1.2(i):** you may not require notifications to be enabled in order to use the app. ✅

---

# 4. Health / Medical Claims — pet health

## 4.1 Apple: do 1.4.1 and 5.1.3 apply to *pet* apps?

**Reading of the text:** ✅
- **1.4.1 Medical Apps**: *"Medical apps that could provide inaccurate data or information, or that could be used for **diagnosing or treating patients**…"* — "patients" reads as human. The named examples (x-rays, blood pressure, body temperature, blood glucose, blood oxygen "using only the sensors on the device") are all human-biometric. **A pet record-keeping app is not a medical app under 1.4.1.**
- **5.1.3 Health and Health Research**: scoped to *"the health, fitness, and medical research context — including from the Clinical Health Records API, HealthKit API, Motion and Fitness, MovementDisorder APIs, or health-related human subject research."* All human-specific APIs. **Not applicable** if you never touch HealthKit.
- **5.1.1(ix) Highly Regulated Fields** names "healthcare" and says such apps *"should be submitted by a legal entity… and not by an individual developer."* ⚠️ Pet care is not a listed regulated field, but **this is a real argument for enrolling as an Organization rather than an Individual** on both stores.

**⚠️ Where it gets non-obvious:** Apple's **2025-updated age-rating questionnaire explicitly asks about "medical or wellness topics."** ✅ You will have to answer it, and you should answer **honestly and conservatively** — you do store vaccination records and medication logs. Expect this to push you to a higher-than-4+ rating (see §9.4).

**Bottom line:** 1.4.1 and 5.1.3 don't formally bind you, **but** the *spirit* of 1.4.1 — "apps should remind users to check with a doctor… before making medical decisions" — is exactly what reviewers will look for the moment you add symptom notes or AI summaries. Comply voluntarily; it's cheap.

Source: [Apple 1.4.1, 5.1.1(ix), 5.1.3](https://developer.apple.com/app-store/review/guidelines/)

---

## 4.2 🟠 Google Play: the Health apps declaration is mandatory for *everyone*

**Rule:** ✅ **All developers with an app published on Google Play must complete the Health apps declaration form** (Play Console → App content), including apps in closed testing, open testing, and production. **Even developers whose apps have no health features must complete the form and certify that no health features are offered.** Exemptions: system services and private apps only.

⚠️ **Date conflict, flagged:** Google's own help page states a completion deadline of **31 August 2024**, while secondary 2026 sources report an "effective 15 July 2026" expansion. Both may be true (original deadline + later expansion of scope). **Treat it as: must be completed before you can publish, full stop.**

**Health Content and Services policy (Play):** ✅
- Apps **regulated as medical devices** must declare that status and provide proof of approval/clearance/certification on request.
- **All other health apps** must include *"a clear disclaimer… indicating that the app is 'not a medical device and does not diagnose, treat, cure, or prevent any medical condition'"* **and** must *"remind users to consult a healthcare professional for medical advice, diagnosis, or treatment."*
- Prohibited: misleading/harmful health functionality, health misinformation contradicting medical consensus.
- Requires a publicly accessible, **non-PDF** privacy policy.

**🔴 The genuinely uncertain bit:** ⚠️ **Neither the Health apps declaration form nor the Health Content and Services policy mentions animal or veterinary health at all.** The category list is human-oriented (disease management, clinical decision support, reproductive health, medication management, physical therapy…). There is no "veterinary" option.

**Recommended approach:**
1. In the Health apps declaration, select **"my app does not offer health features"** — because the form's categories are defined around human health, and "Medication management" there means *the user's own* medications.
2. **But hedge in the app itself**: ship the disclaimer anyway (see §4.4). It costs one screen and removes the argument entirely if a reviewer disagrees with your reading.
3. If Play Console's UI ever forces a health category on you or you get a rejection, re-declare under the closest category and add the disclaimer prominently. Keep a written record of your reasoning.
4. ⚠️ Secondary reporting mentions a **January 2026 enforcement update** adding a "Medical Device" labelling system and stricter Health Connect justifications — **you should not touch Health Connect at all**; it's for human health data and would drag you into a much stricter regime.

Sources: [Health apps declaration form](https://support.google.com/googleplay/android-developer/answer/14738291?hl=en) · [Health Content and Services policy](https://support.google.com/googleplay/android-developer/answer/16679511?hl=en) · [Health app categories](https://support.google.com/googleplay/android-developer/answer/13996367?hl=en)

---

## 4.3 AI features — where the line is

### Google Play AI-Generated Content policy ✅
**Scope:** covers generative AI apps — text-to-text AI chatbots *where the chatbot interaction is a central feature*, text-to-image / voice-to-image / image-to-image apps, and apps creating AI voice/video of real people. **Explicitly excludes**: apps that merely *host* AI content, **summarization-only features**, and **productivity tools using AI to enhance existing functions**.

**🟢 Good news for you:** an AI feature that *summarizes symptom notes into a vet-visit prep sheet* is plausibly **summarization-only / productivity enhancement → out of scope.** ⚠️ But this is a judgement call, and it flips the moment you add a **conversational "ask about your pet's symptoms" chatbot** — that is squarely in scope.

**If in scope, you must:**
- Prevent generation of offensive content per Play's Inappropriate Content policies.
- ⚠️ Provide **in-app user reporting/flagging** so users can report offensive AI output **without leaving the app**, and use those reports to inform filtering/moderation. (Reported consistently across sources including Google's own policy summaries; the specific help page I fetched did not surface this clause — **verify directly in the Play Console policy text before launch**.)
- Comply with all other Play policies.

**Separate and definitely applicable:** ✅ **AI-generated content declaration in Play Console.** *All* developers must declare AI-generated or AI-edited **store-listing assets** (screenshots, feature graphics, promo videos, YouTube videos). Self-declaration model, per asset, via a checkbox in the store-listing/promotional-content flows. Declared assets get an **"AI-label" shown on the Play Store**.
→ **If you generate any marketing screenshots or a hero image with AI, you must tick this.**

**Also applicable:** ✅ Play's **User Data policy now explicitly covers third-party AI**: *"These requirements also apply to third-party AI integrations (such as products, services, code) and you remain responsible for ensuring compliance with this policy, including limited use, disclosure and consent."* → Sending pet symptom notes to OpenAI/Anthropic/Gemini is a **data-sharing event** you must disclose in your privacy policy and Data safety form.

### Apple ✅
- **5.1.2(i)** (current text): *"You must clearly disclose where personal data will be shared with third parties, **including with third-party AI**, and obtain explicit permission before doing so."* → A **one-time, explicit opt-in consent** before the first cloud-AI call is mandatory. An entry buried in the privacy policy is not "explicit permission."
- **1.2 UGC** applies if the AI feature produces content shared into the household feed.
- **February 2026 update:** Guideline 1.2 clarified to cover apps with random or anonymous chat. (Doesn't hit you — your groups are invite-only and identified.)
- **1.4.1** spirit: add the "consult a vet" reminder to any AI health output.

**Architecture recommendation:** prefer **on-device** (Apple Foundation Models / Gemini Nano / a bundled small model) for symptom summarisation. On-device = **not "collected"** under Apple's definition, **no** third-party AI disclosure obligation, **no** cross-border transfer question, and a real marketing differentiator ("your pet's health notes never leave your phone"). If you must use cloud, gate it behind explicit opt-in and a clear disclosure.

Sources: [Play AI-Generated Content policy](https://support.google.com/googleplay/android-developer/answer/14094294?hl=en) · [Declaring AI-generated content in Play Console](https://support.google.com/googleplay/android-developer/answer/17262077?hl=en) · [Best practices to safeguard AI-generated content](https://support.google.com/googleplay/android-developer/answer/16353813?hl=en) · [Play User Data policy](https://support.google.com/googleplay/android-developer/answer/10144311?hl=en) · [Apple 5.1.2(i)](https://developer.apple.com/app-store/review/guidelines/)

---

## 4.4 Disclaimers you should ship

**Minimum set:**

1. **Persistent, in-app, first-run + Settings** (and in the store description):
   > Pet Care Hub is an organisational and record-keeping tool. It is **not a medical device** and does **not diagnose, treat, cure, or prevent any medical condition** in animals or humans. Always consult a qualified veterinarian for advice about your pet's health.

2. **On every AI output screen**, inline and non-dismissible:
   > AI-generated summary — for general guidance only. Not a substitute for professional veterinary advice, diagnosis, or treatment. Always consult your vet.

3. **On the vet-visit-prep and insurance-claim-pack PDFs**, in the document footer: same disclaimer + "generated by the pet owner from self-reported records."

4. **On vaccination/medication reminder screens:**
   > Reminders are a convenience feature. Delivery is not guaranteed. Do not rely on Pet Care Hub as the sole reminder for time-critical medication.
   (This one is as much liability protection as store policy — and it's honest given §3.1.)

**Language that will get you in trouble — never use:** "diagnose," "treat," "prescribe," "clinically proven," "medical-grade," "vet-approved" (unless you have a named vet and a contract), "detects [condition]," "monitors your pet's health" (implies measurement), dosage calculation of any kind (Apple **1.4.2** restricts drug dosage calculators to manufacturers/hospitals/universities/insurers/pharmacies or regulator-approved entities — **do not build a dose calculator**). ✅

---

# 5. User-Generated Content & Sharing

## 5.1 Apple 1.2 — yes, it applies to you

**Rule (verbatim):** ✅ *"Apps with user-generated content or social networking services **must include**:*
- *A method for **filtering objectionable material** from being posted to the app*
- *A mechanism to **report offensive content** and timely responses to concerns*
- *The ability to **block abusive users** from the service*
- ***Published contact information** so users can easily reach you"*

Plus: *"It is your responsibility to remove content that violates this guideline, your terms of service, or your community standards."* And per the **8 June 2026 update**, a new paragraph was added clarifying developer responsibilities for violating content. ✅

**🔴 There is no private-group exemption in the text.** The guideline is unconditional. In practice reviewers apply it proportionately to closed groups, but you cannot rely on that.

**Your UGC surface is real:** pet photos, vet invoices/documents (which could contain anything), free-text symptom notes, pet names, household member display names, invite messages, lost-pet poster text and photos, **and — critically — the lost-pet poster share link, which is public**.

## 5.2 Google Play UGC policy — explicitly names closed groups

**Rule:** ✅ Play requires "robust, effective, and ongoing UGC moderation," proportionate to type:
- **Closed groups** (the policy's example: school/company apps) → **in-app reporting functionality for content and users**.
- **Direct interactions** (messaging, tagging) → **in-app user blocking capability**.
- **Public UGC** → in-app reporting **and** blocking.
- Users must **accept terms of use before creating or uploading content**.
- Apps must **define objectionable content and behaviours** in their policies.

**So Play tells you directly: even a private household group needs in-app reporting.**

## 5.3 What you must build 🔴

| Requirement | Implementation |
|---|---|
| Terms acceptance before first upload | Checkbox/accept screen at onboarding, before any photo/note can be created. Log consent + timestamp + ToS version. |
| Content policy | Publish "Community Guidelines" defining objectionable content; link from Settings and from the website. |
| **Report content** | Long-press / overflow menu on every photo, note, and log entry → "Report". Routes to your abuse inbox. Must work **without leaving the app**. |
| **Report / block user** | On every household member row: "Report member" and "Remove & block". Blocking must actually prevent re-invite by that identity. |
| **Filtering** | For a private household, a lightweight server-side scan of uploaded images is proportionate. At minimum: an upload-time ToS reminder, size/type validation, and a manual review queue. For **public** lost-pet posters — apply real moderation (see below). |
| **Published contact info** | A real support email + a support URL on both store listings *and* in-app Settings → Help. Not a contact form only. |
| **Response SLA** | ⚠️ Community guidance strongly cites a **24-hour** action window for objectionable-content reports. Apple's text says "timely responses." Commit to 24h in your ToS and actually staff it. |
| Owner/admin controls | Household owner can remove a member and revoke their access + optionally purge their contributions. |

## 5.4 🟠 Lost-pet poster share links — your highest-risk surface

A publicly shareable, user-composed poster with a free-text field and a photo is a **public UGC channel**. It can be abused to host harassment, phishing, or explicit imagery on your domain.

**Mitigations (do all of these):**
- Server-side **image moderation** on poster images before the public link goes live (cloud vision safe-search or equivalent).
- **Length-limited, sanitised** text fields; strip URLs from free text.
- Every public poster page carries a **"Report this poster"** link.
- Links **expire** (e.g. 90 days) and are **revocable by the creator and by you**.
- **Rate-limit** poster creation per account; gate behind an authenticated, verified-email account.
- No indexable user-controlled HTML; `noindex` unless the user opts in.

## 5.5 Invite / deep links

**Policy position:** ✅ Neither store prohibits invite links. But:
- Invite tokens must be **single-use or scoped**, **expiring**, and **revocable**. An enumerable/permanent invite URL that grants access to a household's pet records and photos is a data-breach vector and would be a legitimate rejection under Apple 5.1.1 / Play User Data.
- **Time-limited guest access for sitters** is exactly right — enforce it **server-side**, not just in the UI.
- Use **Android App Links** (verified via `assetlinks.json`) and **iOS Universal Links** (`apple-app-site-association`), not custom URL schemes — custom schemes can be hijacked by other apps.
- **Apple 4.2.3(i):** your app must work on its own; don't make the invite flow require another app.
- Apple's UGC guideline bans apps used primarily for "random or anonymous chat" (clarified Feb 2026) — your invite model is identified and consented, so you're fine, **provided** invites can't be used to cold-contact strangers. Don't build a "find a sitter near you" directory without a lot more thought.
- **Don't** ask for contacts permission to power invites (see §3.5).

Sources: [Apple 1.2](https://developer.apple.com/app-store/review/guidelines/) · [Updated App Review Guidelines, 8 Jun 2026](https://developer.apple.com/news/?id=a233fmpw) · [Updated App Review Guidelines, 6 Feb 2026](https://developer.apple.com/news/?id=d75yllv4) · [Play User Generated Content policy](https://support.google.com/googleplay/android-developer/answer/9876937?hl=en)

---

# 6. Store Listing, Metadata & Review

## 6.1 Google Play metadata

**Rules:** ✅/⚠️
- **App name: 30 characters max.** ✅
- Short description 80 chars; full description 4,000 chars.
- Screenshots: **min 2, max 8** per phone type; JPEG or 24-bit PNG (no alpha); ≤ 8 MB each; 16:9 or 9:16. Tablet: min 4 if you declare tablet support. ⚠️
- Metadata must be "clear, relevant, well written, and accurate"; misleading, improperly formatted, irrelevant, excessive or inappropriate metadata is prohibited. ✅
- **Prohibited in title/icon/screenshots:** ⚠️ emojis; ALL CAPS (unless it's your brand); performance/ranking claims ("#1," "Best," "App of the Year," award icons); price or promo language ("Free," "No ads," "50% off," "Limited time"); calls to action ("Download now," "Install"); anything indicating store performance or ranking.

**Specific to you:** ⚠️ Your key differentiator is "**Unlimited pets on one price**." That phrase contains price/promotional language and **should not appear in the title, icon, or screenshot overlays**. Put it in the **long description** and on the in-app paywall instead. Safe title pattern: `Pet Care Hub: Pet Records` or `Pet Care Hub — Family Pet Care`.

Sources: [Play Metadata policy](https://support.google.com/googleplay/android-developer/answer/9898842?hl=en) · [Best practices for your store listing](https://support.google.com/googleplay/android-developer/answer/13393723?hl=en)

---

## 6.2 Apple metadata & screenshots

- **2.3.8:** *"Metadata should be appropriate for all audiences, so make sure your app and in-app purchase icons, screenshots, and previews adhere to a **4+ age rating** even if your app is rated higher."* ✅ (Relevant: don't put graphic wound/injury photos in screenshots; a **memorial-mode** screenshot showing a deceased pet is fine but keep it tasteful.)
- **2.3 (accurate metadata):** title, subtitle, description, keywords and screenshots must reflect the app's **actual current** core experience. Don't screenshot the Apple Watch app if it isn't shipping. ✅
- **2.3.1:** no hidden/undocumented features; don't ship dormant code paths for features you'll "enable later."
- App name 30 chars, subtitle 30 chars, promo text 170 chars, description 4,000 chars, keywords 100 chars.

Source: [Apple 2.3](https://developer.apple.com/app-store/review/guidelines/)

---

## 6.3 Apple 2.1 App Completeness & demo account

**Rule (verbatim):** ✅ *"Submissions… should be final versions with all necessary metadata and fully functional URLs included; placeholder text, empty websites, and other temporary content should be scrubbed before submission… include demo account info **(and turn on your back-end service!)** if your app includes a login."*

*"If you are unable to provide a demo account due to legal or security obligations, you may include a **built-in demo mode** in lieu of a demo account with prior approval by Apple. Ensure the demo mode exhibits your app's **full features and functionality**."*

**2.1(b):** in-app purchases must be "complete, up-to-date, visible to the reviewer and functional." If a configured IAP can't be found in the app, **explain why in the review notes**. ✅

**🔴 What this means for a household-sharing app — reviewers will get stuck here.** Provide in App Review Information → Notes:
1. A **working demo account** (email + password), **not** requiring an email OTP the reviewer can't receive. If you use magic links, provide a bypass code and document it.
2. **A second demo account** that is already a *member* of the first account's household, so the reviewer can see shared logging without doing an email round-trip. Apple's own guidance says to use the Notes field for multiple account types.
3. **A pre-seeded household** with 2 pets, photos, feeding logs, a vaccination record, and an active reminder — so the app isn't empty.
4. **A pre-generated, still-valid invite link** pasted into the notes.
5. A **step-by-step script**: "1. Sign in with A. 2. Tap Household → Invite. 3. Sign out, sign in with B, tap the link in the notes…"
6. Note where the paywall is, that the subscription is sandbox-testable, and **that Restore Purchases is on the paywall**.
7. Note that **no location or contacts data is used**.
8. If an AI feature is behind a flag, either enable it for review or say it's disabled and not present in this build.

Do the exact equivalent in **Play Console → App content → App access**: provide credentials and instructions for every gated part of the app. ✅

Sources: [Apple 2.1](https://developer.apple.com/app-store/review/guidelines/) · [Apple App Review](https://developer.apple.com/distribute/app-review/) · [Prepare your app for review (Play)](https://support.google.com/googleplay/android-developer/answer/9859455?hl=en)

---

## 6.4 Apple 4.2 Minimum Functionality

**Rule:** ✅ *"Your app should include features, content, and UI that elevate it beyond a repackaged website… If your App doesn't provide some sort of lasting entertainment value or adequate utility, it may not be accepted."* **4.2.3(i):** must work on its own without requiring another app.

**Verdict:** 🟢 Low risk. Multi-user real-time sync, notifications, widgets, PDF generation, and offline-capable native storage is comfortably "app-like." **But**: if you ship a KMP/Compose Multiplatform iOS build, make sure it *feels* native — reviewers reject things that look like a wrapped web view. Use native navigation gestures, respect Dynamic Type, support Dark Mode, and handle the iOS 26 Liquid Glass appearance deliberately (see §7.2).

---

## 6.5 🟠 Google Play closed testing — the 12-tester / 14-day rule

**Rule:** ✅ Developers with **personal** Play Console accounts created **after 13 November 2023** must run a **closed test with at least 12 testers opted in continuously for at least 14 days** before applying for production access.
- **Organization accounts and personal accounts created before 13 Nov 2023 are exempt.** ✅
- Originally 20 testers; reduced to 12 in December 2024. The 14-day duration never changed. ✅
- ⚠️ 2026 reports: Google now also checks that testers **genuinely used the app**, and all 12 must overlap within the **same continuous 14-day window** — a dropout resets the counter.

### 🔴 Strongest single recommendation in this report: **register as an Organization, not an Individual, on both stores.**
Benefits, all at once:
- **Skips the 12-tester/14-day Play requirement entirely.** ✅
- Publishes under a company name (better for a paid consumer app with health-adjacent content).
- Addresses Apple **5.1.1(ix)**'s preference for legal entities in sensitive/regulated-adjacent areas. ✅
- Makes the **DSA trader** declaration straightforward (see §6.7).
- Aligns with the 2026 **Android developer verification** regime (below).

Costs: a registered legal entity + a **D-U-N-S number** (free, ~5–14 business days from Dun & Bradstreet) for both Apple Organization enrolment and Play organization verification. Worth it.

Sources: [App testing requirements for new personal developer accounts](https://support.google.com/googleplay/android-developer/answer/14151465?hl=en) · ⚠️ secondary 2026 reporting on tester-engagement checks

---

## 6.6 Play developer verification & account requirements

- **Verify your developer identity** in Play Console is required. Personal accounts submit government ID; organizations submit a **D-U-N-S number** plus organization documents. ✅
- ⚠️ **2026 Android Developer Verification** extends identity verification **beyond the Play Store to all app distribution on certified Android devices, including sideloaded APKs**. Reported first rollout: **30 September 2026** for users in **Brazil, Indonesia, Singapore, Thailand**, expanding after.
- ✅ **Play's 15 July 2026 announcement** confirms: **all Play apps must be registered in Play Console**, and this applies to apps distributed on Google Play **and outside it**; non-compliance risks global removal.
- **Apple Developer Program: $99 USD/year**, same price for Individual and Organization; Organization requires a D-U-N-S number. ✅ Fee waivers exist for eligible nonprofits/education/government publishing free apps.

Sources: [Verify your developer identity (Play)](https://support.google.com/googleplay/android-developer/answer/10841920?hl=en) · [Understanding Android developer verification](https://support.google.com/android-developer-console/answer/16561738?hl=en) · [Play policy announcement: July 15, 2026](https://support.google.com/googleplay/android-developer/answer/17134731) · [Apple Developer Program membership](https://developer.apple.com/programs/whats-included/) · [Apple fee waivers](https://developer.apple.com/help/account/membership/fee-waivers/)

---

## 6.7 🟠 EU DSA trader status — both stores

**Rule:** ✅ DSA Articles 30 & 31 require the stores to verify and display trader contact info (address, phone, email) on the product page for all traders distributing in the EU.

**Apple:** ✅ Since **18 February 2025**, apps without a verified trader status **have been removed from the App Store in the EU** until status is provided and verified. ⚠️ Reported: you must complete the declaration **even if you don't distribute in the EU** and even as a solo developer with a free app — at minimum declaring "non-trader." Removed apps are reinstated after verification.

**Google Play:** ✅ Same DSA obligation; declare trader status in Play Console. Non-compliance → removal or limited discoverability/functionality in the EU. ⚠️ (Google's implementation is less publicly documented than Apple's; check Play Console for the current form location.)

**You will be a trader** (you're selling a subscription commercially in the EU). Prepare: legal entity name, registered address, **a working phone number**, and an email — all of which will be **publicly displayed on your product pages**. 🔴 **Do not use your home address.** Use a registered office / virtual office address, and a business VoIP number.

Sources: [Apps without trader status removed from the EU App Store](https://developer.apple.com/news/?id=einwn76m) · [Manage EU DSA trader requirements (App Store Connect)](https://developer.apple.com/help/app-store-connect/manage-compliance-information/manage-european-union-digital-services-act-trader-requirements/) · [DSA trader status upcoming requirement](https://developer.apple.com/news/upcoming-requirements/?id=02172025a)

---

# 7. Technical Requirements — 2026

## 7.1 Android / Google Play

| Requirement | Detail | Deadline | Src |
|---|---|---|---|
| **Target API level** | New apps **and** updates must target **Android 16 (API 36)** or higher. Wear OS & Automotive: API 35+. Android TV & XR: API 34+. Extension available to **1 Nov 2026**. Existing apps must target API 35+ to stay available to new users on newer OS versions. | **31 Aug 2026 (passed)** | ✅ [link](https://support.google.com/googleplay/android-developer/answer/11926878?hl=en) |
| **16 KB page size** | All new apps and updates targeting Android 15+ devices must support 16 KB memory page sizes. ⚠️ Original deadline 1 Nov 2025; Play Console reportedly offered extension to **31 May 2026**. Both now passed. | **Passed — mandatory** | ⚠️ [blog](https://android-developers.googleblog.com/2025/05/prepare-play-apps-for-devices-with-16kb-page-size.html) |
| **Play Billing Library 8+** | All new apps and updates using Play billing must use **Billing Library 8.0.0+**. Extension available to 1 Nov 2026. Existing v7 binaries keep transacting; you just can't ship updates. | **31 Aug 2026 (passed)** | ⚠️ [migration guide](https://developer.android.com/google/play/billing/migrate-gpblv8) |
| **Play App Signing** | Required for new apps. Google holds the app signing key; you hold an upload key and ship an AAB. | Now | ✅ [link](https://support.google.com/googleplay/android-developer/answer/9842756?hl=en) |
| **Android App Bundle (AAB)** | Required for new apps since Aug 2021. | Now | ✅ |
| **Play Integrity API** | Optional but recommended; replaces SafetyNet Attestation. Device/app/account integrity signals. | Optional | ⚠️ |

### 🔴 16 KB page size + KMP — action required
This is the one that quietly breaks Kotlin Multiplatform projects, because it's about **native `.so` libraries**, which KMP/Compose apps pull in transitively. Audit for:
- **SQLite/SQLCipher** (`libsqlite3.so`, `libsqlcipher.so`) — very likely if you use SQLDelight or Room with an encrypted DB.
- **Realm / ObjectBox** if used.
- Image decoders (Coil's native paths, libwebp, libjpeg-turbo).
- **PDF rendering/generation** libraries — high risk for your insurance-pack export.
- Crypto/protobuf/gRPC natives.
- Any Firebase/Crashlytics NDK component.

**Check:** `zipalign -c -P 16 -v 4 app-release.apk`, or use Google's `check_elf_alignment.sh`. **Fix:** NDK r27+ / AGP 8.5.1+ and rebuild each `.so` with `-Wl,-z,max-page-size=16384`, or upgrade the dependency to a 16KB-ready release.

---

## 7.2 Apple / iOS

| Requirement | Detail | Deadline | Src |
|---|---|---|---|
| **Minimum SDK** | Apps uploaded to App Store Connect must be built with **Xcode 26+** using the **iOS 26 SDK** (or tvOS/visionOS/watchOS 26 SDK for those platforms). | **28 Apr 2026 (passed)** | ✅ [link](https://developer.apple.com/news/?id=ueeok6yw) |
| **Liquid Glass** | Apps built with the iOS 26 SDK get the Liquid Glass look on native UI **by default** unless you explicitly opt out. Building with the new SDK ≠ raising your deployment target. | Now | ⚠️ |
| **Privacy manifest** | `PrivacyInfo.xcprivacy` required. Apps not describing their **required-reason API** use are **rejected at App Store Connect upload** since 1 May 2024. | Now | ✅ [link](https://developer.apple.com/documentation/bundleresources/privacy-manifest-files) |
| **SDK signatures** | Third-party SDKs on Apple's "SDKs that require a privacy manifest and signature" list must ship a privacy manifest **and a signature**. | Now | ✅ |
| **Age rating questionnaire** | Updated questionnaire (13+/16+/18+ added; 12+/17+ removed) had to be answered by **31 Jan 2026** or submissions are blocked. | **Passed** | ✅ [link](https://developer.apple.com/news/?id=ks775ehf) |
| **Social media declarations** | Responses to the new social-media questions in the age-rating questionnaire are **required for all new apps and updates from September 2026**. | **Now** | ✅ [link](https://developer.apple.com/news/?id=tlur8uvi) |
| **App Attest** | Optional. Device-attestation for your backend. | Optional | — |

### 🔴 Privacy manifest + KMP — action required
A **KMP iOS framework does not automatically carry a privacy manifest.** You must:
1. Add `PrivacyInfo.xcprivacy` to your **iOS app target**, declaring `NSPrivacyCollectedDataTypes` (match your Nutrition Labels exactly) and `NSPrivacyAccessedAPITypes`.
2. **Required-reason APIs you will almost certainly trip:**
   - `NSPrivacyAccessedAPICategoryUserDefaults` → **KMP `multiplatform-settings` / `NSUserDefaults` uses this.** Reason code `CA92.1` (access info from app itself / app group).
   - `NSPrivacyAccessedAPICategoryFileTimestamp` → any file-manager/attachment code. Reason `C617.1` or `0A2A.1`.
   - `NSPrivacyAccessedAPICategoryDiskSpace` → media/attachment write checks. Reason `E174.1` / `85F4.1`.
   - `NSPrivacyAccessedAPICategorySystemBootTime` → some analytics/logging. Reason `35F9.1`.
3. Audit **every** third-party SDK (Firebase, RevenueCat, Sentry, Ktor's transitive deps) for a bundled manifest + signature. **Xcode combines all manifests into a single Privacy Report** — generate it (`Product → Archive → Generate Privacy Report`) and diff it against your declared Nutrition Labels before every submission.
4. If you use **CocoaPods/SPM** to consume the KMP framework, confirm the manifest actually lands in the built product.

⚠️ Note: `NSPrivacyTracking` should be `false` and `NSPrivacyTrackingDomains` empty unless you've added ad/attribution SDKs.

Sources: [Privacy manifest files](https://developer.apple.com/documentation/bundleresources/privacy-manifest-files) · [Adding a privacy manifest](https://developer.apple.com/documentation/bundleresources/adding-a-privacy-manifest-to-your-app-or-third-party-sdk) · [Upcoming SDK minimum requirements](https://developer.apple.com/news/?id=ueeok6yw)

---

# 8. Widgets, Apple Watch & Wear OS

## 8.1 Apple — widgets & Live Activities
- ✅ **2.5.16:** *"App Clips, widgets, extensions, and notifications must be related to the app's content and functionality."* A "last fed 3h ago" widget is directly related — fine.
- ✅ **3.1.7:** **no advertising** in extensions, widgets, notifications, keyboards, or watchOS apps.
- 🆕 ✅ **4.5.3 (added 8 June 2026):** *"Live Activities may not be used to spam, phish, or send unsolicited messages to customers."*
  → If you use a **Live Activity** for an in-progress dog walk or a medication window, keep it strictly functional: no promos, no "upgrade to Premium" in the Dynamic Island, no re-engagement bait.
- Widgets must **not require a subscription to be installed** but *may* show a "subscribe to unlock" state — keep it tasteful; a widget that is purely an upsell risks 2.5.16 / 4.2.
- Support all required widget families and provide a sensible **placeholder/redacted** state for the widget gallery — reviewers screenshot the gallery.
- App Intents / interactive widgets for "Log feeding" from the home screen is a strong differentiator and helps with 4.2 and 4.3(b).

## 8.2 Apple Watch
- A watchOS app must be **useful on its own terms** — a watch app that is only a "open your phone" shortcut invites 4.2. A quick-log complication + "last fed" complication is genuinely useful.
- No ads (3.1.7).
- **Watch apps must be built with the watchOS 26 SDK** (see §7.2). ✅
- Your subscription must work on watchOS — Apple 3.1.2(a): *"Subscriptions must work on all of the user's devices where the app is available."* ✅ Practically: sync entitlement from the phone; don't build a separate paywall on the watch.

## 8.3 Wear OS
- ✅ **Target API:** existing Wear OS apps must target **API 34+ by 31 Aug 2026**; new Wear OS submissions must target API 35+ (per the general target-API table).
- ⚠️ **As of 15 September 2026, all Wear OS apps must support 64-bit devices.**
- ⚠️ If you ship **Tiles** or **Complications**, you must **mention that support in your Play store listing**; tiles should reference the ongoing activity if present in the carousel.
- Meet the **Wear OS app quality guidelines** (visual, functional, performance, and Play-listing criteria) — these are enforced at review for the Wear form factor.
- Wear OS apps are distributed via a separate APK in the same app bundle or a standalone listing; declare form-factor support in Play Console.

Sources: [Apple 2.5.16 / 3.1.7 / 4.5.3](https://developer.apple.com/app-store/review/guidelines/) · [Updated App Review Guidelines 8 Jun 2026](https://developer.apple.com/news/?id=a233fmpw) · [Wear OS app quality](https://developer.android.com/develop/adaptive-apps/quality-guidelines/wear-app-quality) · [Target API level requirements](https://support.google.com/googleplay/android-developer/answer/11926878?hl=en)

---

# 9. Anything Else That Could Get You Rejected or Removed

## 9.1 🆕 🟠 Apple 4.3(b) — the new low-quality / saturation rule (June 2026)

⚠️ **The most significant new risk in this report.** The June 2026 guidelines update expanded 4.3:
- **4.3(a)** — spam/duplicate apps: clarified basis, example added.
- **4.3(b)** — Apple may reject **and remove** apps that are "**indistinguishable**" from apps already on the store. Named "well-established" categories (dating, flashlight, sound effects, wallpaper, simple timers, fortune telling) **will not be accepted as new submissions "unless they offer a meaningfully different or improved experience."** Apple further states it "may remove these apps from the App Store going forward if they are not updated, improved, or do not attract customers."

**Pet trackers are not on the named list — but the list is explicitly non-exhaustive ("including"), and "pet care tracker" is a crowded category.** ⚠️

**Defence (build these into v1, not v2):**
- Lead with **household multi-user real-time sharing + time-limited sitter access** — this is genuinely uncommon and is your 4.3(b) answer.
- Ship **interactive widgets**, **Watch/Wear quick-log**, and the **insurance claim-pack PDF export** at launch, not later. Concrete, differentiated capabilities.
- Write the **App Review notes** to explicitly say what makes this different from existing pet trackers. Reviewers read them.
- Never submit near-duplicate binaries/bundle IDs. One app, one listing, variations via IAP (4.3's own guidance).
- Keep shipping meaningful updates post-launch — 4.3(b) now has a *removal* teeth for stale apps.

Sources: [Updated App Review Guidelines, 8 Jun 2026](https://developer.apple.com/news/?id=a233fmpw) · [Apple 4.3](https://developer.apple.com/app-store/review/guidelines/)

---

## 9.2 Copycat naming — "11pets" and friends

**Rule:** ✅ **Apple 4.1 Copycats** — apps that copy other apps won't pass review; accounts that repeatedly submit copycats or impersonate a service **will be closed**. **Apple 5.2.1** — don't use protected third-party material (trademarks, copyrighted works) or "misleading, false, or copycat representations, names, or metadata in your app bundle or developer name." **Play Impersonation policy** is equivalent.

**Do:**
- Run a trademark clearance search on "Pet Care Hub" in US (USPTO), EU (EUIPO), UK (IPO), and CA before launch. "Pet Care" is descriptive — check whether a composite mark blocks you.
- ⚠️ Avoid keyword-stuffing competitor brand names ("11pets", "Pawtrack", "Dog Log") into your **keywords field, title, subtitle, or description**. Apple and Google both treat competitor-brand keyword stuffing as a 4.1/metadata violation, and it's a classic trigger for a takedown request from the competitor.
- Don't imitate a competitor's icon, colour scheme, or screenshot layout.

## 9.3 🟠 Breed images & third-party content (copyright)

**Rule:** ✅ Apple 5.2.1 — using protected third-party material without permission is a rejection; you may be asked for **documented evidence of your right to use the content**. Play's Intellectual Property policy is equivalent.

**Concrete risks in your app:**
- **Breed reference images** — do not scrape. Use a properly licensed source (paid stock with an app/embedded-use licence) or commission illustrations. Keep the licence PDFs on file; Apple *does* ask.
- ⚠️ **AI-generated icons/artwork are a documented rejection vector under 5.2.2** — reviewers have rejected apps for AI-generated icons that resemble protected IP. If you use AI art, keep provenance records and avoid anything that looks like a recognizable character or brand.
- **Vet/insurer logos** in the "share with your vet" or claim-pack UI — don't display third-party brand logos without permission.
- **Breed standard text** from kennel clubs (AKC, The Kennel Club, FCI) is copyrighted. Write your own.
- **Vaccination schedules / parasite protocols** — if sourced from a manufacturer or veterinary association, you need permission or you must paraphrase from primary facts.
- **Wikipedia/Wikimedia images** — mostly CC-BY-SA, which requires attribution **and may impose share-alike obligations**. Read the licence per image; don't bulk-import.

Sources: [Apple 4.1 / 5.2](https://developer.apple.com/app-store/review/guidelines/) · [Apple copyright and trademark guidelines](https://www.apple.com/legal/intellectual-property/guidelinesfor3rdparties.html)

---

## 9.4 Children, COPPA & age ratings — confirming your approach

**Your stated position (not targeting kids) is correct. Confirm it explicitly in both consoles.**

**Google Play:** ✅ In Play Console → **Target audience and content**, select adult age groups only (**18+**, or 13+ if you prefer). You may also tick "**my app may unintentionally appeal to children**" — Google notes that *"if you choose to include imagery and terminology in your app that could be considered targeting children, this may impact Google Play's assessment of your declared target audience."*
→ **Practical consequence: do not use cartoon mascots, bubbly rounded fonts, sticker/reward mechanics, or "kid-friendly" language in your store assets.** A cute dog illustration is fine; a cartoon puppy character with big eyes in a playful font reads as child-directed and can drag you into the **Families policy**, which brings certification requirements, ad-SDK restrictions, and COPPA/GDPR-K obligations you absolutely do not want.
→ **Do not opt into the Designed for Families programme.**

**Apple:** ✅ Complete the updated questionnaire (4+/9+/13+/16+/18+). Given the medical/wellness topic question and UGC/sharing, expect **13+** (or higher). **Do not select the Kids Category.** From **September 2026**, you must also answer the **social media capability questions** — your household feed is *private and invite-only*, which is **not** "redistribute, amplify, or interact with UGC through a social feed or similar discovery method." ⚠️ Answer "no" to social media capabilities, but be ready to justify it; note that if you *do* declare social media capabilities and disable them under 13, you avoid the Social Media Time Allowance category.

**COPPA:** as long as you don't direct the app to children under 13 and don't have actual knowledge of under-13 users, COPPA's specific obligations don't attach. ⚠️ **But invited household members could be children.** Mitigation: ToS minimum age **16** (satisfies the strictest EU digital-consent age), don't collect DOB, and make invite acceptance require agreeing to the age-gated terms.

Sources: [Manage target audience and app content settings](https://support.google.com/googleplay/android-developer/answer/9867159?hl=en) · [Google Play Families Policies](https://support.google.com/googleplay/android-developer/answer/9893335?hl=en) · [Apple: Updated age ratings](https://developer.apple.com/news/?id=ks775ehf) · [Apple: Social media questions](https://developer.apple.com/news/?id=tlur8uvi)

---

## 9.5 🟠 European Accessibility Act — the non-store legal landmine

**Rule:** ⚠️ The EAA became **enforceable on 28 June 2025**. It applies to **e-commerce services**, explicitly including **mobile apps**, across the EU — and to **non-EU companies selling into the EU**. The technical standard is **EN 301 549** (current V3.2.1 incorporates **WCAG 2.1 Level AA**; V4.1.1 expected in 2026 will move to WCAG 2.2). Organizations must publish an **accessibility statement**. Penalties: fines and removal from the EU market (reported up to €100,000 per violation in Germany).

**Why it hits you:** you sell a subscription to EU consumers through an app. That's an e-commerce service.

⚠️ There is a **microenterprise exemption** for *services* provided by companies with **<10 employees and ≤ €2M annual turnover** — most small indie teams will qualify. **Verify this against the transposition law of each member state you sell in**, as national implementations vary. Do not assume it applies.

**Minimum practical work (do it regardless — it's also good for App Store quality):**
- Full **TalkBack / VoiceOver** labelling on every interactive element, especially the paywall, invite flow, and log-entry buttons.
- **Dynamic Type / font scaling** to 200% without clipping or loss of function.
- **Colour contrast ≥ 4.5:1** for text; never convey pet status by colour alone (add an icon or label).
- **Touch targets ≥ 44×44pt / 48×48dp.**
- Respect **Reduce Motion**.
- Ensure your **generated PDFs are tagged/accessible** (this is genuinely hard — flag as an engineering task).
- Publish an **accessibility statement** on your website.

Sources: ⚠️ [EAA & EN 301 549 (Level Access)](https://www.levelaccess.com/blog/eu-accessibility-requirements-and-eaa-compliance/) · ⚠️ [EN 301 549 (Deque)](https://www.deque.com/en-301-549-compliance/) — **flagged: no official Apple/Google source; this is EU law. Get legal advice for EU launch.**

---

## 9.6 Deceptive subscription patterns — the specific things that get apps pulled

Both stores' anti-scam language is now sharp (Apple 3.1.2(a): apps that "trick users into purchasing a subscription under false pretenses or engage in bait-and-switch and scam practices" → removal *and possible developer-account termination*). ✅

**Never ship:**
- A paywall with **no visible close/dismiss control** (named Play violation ✅; also a common Apple 3.1.2 rejection).
- A **fake X** that opens another offer, or a close button that appears after a delay without indication.
- **"Free" as the dominant word** when a charge follows (Play explicitly bans SKU names like "Free Trial" for auto-charging products ✅).
- **Monthly-equivalent price larger/bolder than the actual annual charge** (Apple: billed amount must be the *most prominent* pricing element ✅; Play names misleading monthly breakdowns as a violation ✅).
- **Pre-ticked** upgrade checkboxes.
- **Hidden trial-end date.** Show the exact date: "Free until 29 September 2026, then €39.99/year."
- A **cancellation flow with guilt/fear screens** ("Your pets will lose their records!") or extra confirmation steps. ⚠️
- **Reducing benefits after the first period** (named Play violation ✅).
- Requiring social posts, contact uploads, or review prompts to unlock features (**Apple 3.1.2(a)** and **3.2.2(vi)** ✅).
- **Requiring notifications or ATT** to access features (**Apple 5.1.2(i)** ✅).

**Also:** don't over-prompt for App Store ratings — use `SKStoreReviewController`/Play In-App Review APIs and respect the system rate limits.

---

## 9.7 Memorial mode — handle with care 🟡

Not a policy violation, but a review-and-PR risk. Recommendations:
- Keep imagery and copy **respectful and never playful**; Apple 2.3.8 requires screenshots to be 4+-appropriate.
- **Never monetize grief.** Do not gate memorial mode behind the paywall, do not upsell during it, and suppress all promotional notifications for a memorialized pet. A paywall shown to a grieving user is the kind of thing that ends up on social media.
- Offer **export + delete** of the memorialized pet's records.
- If a memorial page can be **shared publicly**, it's public UGC — apply §5.4 moderation.

---

## 9.8 Miscellaneous

- **Background location:** you're not using it. ✅ Keep it that way — background location is one of the most scrutinized Play permissions (requires a Play Console declaration, a demo video, and a review), and a "find my pet" feature would change your compliance profile substantially.
- **Apple 5.1.1(viii):** don't compile personal information from public databases. If you ever add a "find local vets" directory, source it from a licensed provider, not scraping.
- **Apple 4.2.6 Template services:** irrelevant unless you white-label the app for breeders/clinics — if you ever do, read this guideline first.
- **Play Content Ratings:** ✅ Per the 15 July 2026 announcement, **unrated apps are prohibited**. Complete the IARC content-rating questionnaire.
- **Play "App access" declaration:** required so reviewers can reach gated features. ✅
- **Privacy policy hosting:** Play requires a **publicly accessible, non-PDF** URL, active globally, naming the app/company, with contact info. ✅ A Notion page or a `/privacy` route on your marketing site both work; a Google Doc does not.
- **Insurance claim-pack export:** ⚠️ If you ever *partner* with an insurer, or receive commission for referrals, you move toward regulated financial-services promotion (FCA in the UK, IDD in the EU). A neutral "export a PDF the user submits themselves" is safe. Referral fees are not — get advice first.

---

# 📋 Consolidated Compliance Checklist

## ✅ BOTH stores

**Legal entity & accounts**
- [ ] 🔴 Register a **legal entity** and obtain a **D-U-N-S number** (free, allow 2 weeks).
- [ ] 🔴 Enroll as **Organization** on Apple ($99/yr) and Google Play ($25 one-time) — avoids the 12-tester rule and satisfies Apple 5.1.1(ix).
- [ ] 🔴 Declare **EU DSA trader status** in both consoles. Use a **business address and phone** (publicly displayed).
- [ ] Complete developer identity verification on both platforms.

**Legal documents (all live at HTTPS, non-PDF, globally reachable, before submission)**
- [ ] Privacy Policy — covers what's collected, how, all uses, third-party sharing (incl. any AI provider), **retention/deletion**, how to revoke consent, contact info, company + app name.
- [ ] Terms of Use / EULA — includes objectionable-content prohibition, 16+ age minimum, auto-renewal terms, disclaimer of veterinary advice.
- [ ] Community Guidelines (defines objectionable content).
- [ ] **Public account-deletion request page** (no login required, direct link) — reuse for Play's required URL and Apple's optional Privacy Choices URL.
- [ ] Accessibility statement (EAA).
- [ ] Support email + support URL, published on both listings and in-app.

**Subscriptions / paywall (build once, use everywhere)**
- [ ] Subscription **name + duration** + what you get.
- [ ] **Full billed price** as the most prominent pricing element (annual total > monthly equivalent, visually).
- [ ] Explicit **auto-renewal statement**: "Renews automatically at €X/year unless cancelled at least 24h before the period ends."
- [ ] **Trial**: duration, exact end date, price after, and how to cancel before charging.
- [ ] **Restore Purchases** button.
- [ ] **Terms of Use + Privacy Policy** links.
- [ ] **Visible close/dismiss** control.
- [ ] **Cancellation path** in Settings, ≤ 2 taps, deep-linking to the store's subscription centre. No guilt screens.
- [ ] Full **localization** of all price and term strings for every market.
- [ ] CTA labelled with the obligation to pay ("Subscribe — €39.99/year").
- [ ] Never gate the free tier behind notifications, ATT, or social actions.

**Accounts & data**
- [ ] **In-app "Delete Account"** button in Settings — deletes account + associated data, not a deactivation, not a mailto.
- [ ] Define and implement **household-owner deletion** semantics (transfer or dissolve).
- [ ] Revoke Sign in with Apple tokens via Apple's REST API on deletion.
- [ ] Data safety form ⟷ Nutrition Labels ⟷ privacy policy ⟷ actual behaviour: **all four consistent**.
- [ ] TLS everywhere, including image/PDF upload and download.

**UGC & sharing**
- [ ] ToS acceptance gate **before** first upload; log consent + version + timestamp.
- [ ] **Report content** on every photo / note / log entry.
- [ ] **Report user** + **block/remove member** in household management.
- [ ] Abuse inbox with a **24-hour** response commitment, staffed.
- [ ] Server-side **image moderation** on any publicly shareable asset (lost-pet posters).
- [ ] Invite tokens: scoped, **expiring**, single-use, revocable; sitter time limits enforced **server-side**.
- [ ] Verified App Links / Universal Links (no custom schemes).

**Health-adjacent content**
- [ ] "Not a medical device / consult a vet" disclaimer: first run, Settings, every AI output, every exported PDF footer.
- [ ] No dosage calculator. No diagnostic claims. Ban "diagnose/treat/prescribe/clinically proven/vet-approved" from all copy.
- [ ] Reminder-reliability disclaimer for medication.

**AI (if shipping)**
- [ ] Prefer **on-device** inference.
- [ ] If cloud: explicit **one-time opt-in** before first call (Apple 5.1.2(i) third-party AI); disclose the provider in the privacy policy; declare the sharing in Data safety.
- [ ] In-app **reporting/flagging of AI output**.
- [ ] Label AI-generated content in-app.

**Metadata & assets**
- [ ] No price/promo words, no "#1"/"Best", no emojis, no ALL CAPS, no CTAs in title/icon/screenshots.
- [ ] Move "Unlimited pets on one price" to the long description and the paywall only.
- [ ] Screenshots reflect the **shipped** build only (no Watch app in screenshots if it isn't in v1).
- [ ] All screenshots 4+-appropriate (Apple 2.3.8).
- [ ] Licence documentation on file for every third-party image/illustration/font.
- [ ] Trademark clearance on "Pet Care Hub" in US/EU/UK/CA.
- [ ] No competitor brand names in keywords/title/description.

**Accessibility (EAA)**
- [ ] VoiceOver/TalkBack labels on all controls, paywall included.
- [ ] Dynamic Type / font scaling to 200%.
- [ ] Contrast ≥ 4.5:1; never colour-only status.
- [ ] Touch targets ≥ 44pt / 48dp; Reduce Motion respected.
- [ ] Tagged/accessible exported PDFs.

---

## 🤖 GOOGLE PLAY only

**Console declarations (App content page — all mandatory)**
- [ ] **Data safety form** (+ deletion URL + in-app deletion answer + encryption-in-transit).
- [ ] **Health apps declaration** — ⚠️ mandatory for *every* app. Recommended answer: "no health features" (see §4.2), with the disclaimer shipped anyway as a hedge.
- [ ] **Target audience & content** → adult age groups only; **do not** opt into Designed for Families.
- [ ] **Content rating** (IARC questionnaire) — unrated apps are prohibited.
- [ ] **Ads declaration** → "No ads" (assuming subscription-only).
- [ ] **App access** → demo credentials + step-by-step instructions for household sharing.
- [ ] **Privacy policy URL** field.
- [ ] **Government apps / financial features / news** → N/A, but answer them.
- [ ] **AI-generated content declaration** — tick for any AI-made store-listing asset.

**Permissions — merged-manifest audit**
- [ ] 🔴 Declare `SCHEDULE_EXACT_ALARM`. **Do NOT declare `USE_EXACT_ALARM`** (upload will be blocked).
- [ ] 🔴 Implement `canScheduleExactAlarms()` guard + `ACTION_REQUEST_SCHEDULE_EXACT_ALARM` flow + **working inexact fallback** + permission-revoked broadcast handling.
- [ ] 🔴 **Remove** `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_EXTERNAL_STORAGE` from all version codes — check the **merged** manifest for transitive injections. Use **Android Photo Picker** + SAF.
- [ ] **Do NOT** request `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`.
- [ ] **Do NOT** request `READ_CONTACTS` — share-sheet invites only (Contact Picker policy effective 27 Jan 2027).
- [ ] No location permissions of any kind.
- [ ] `POST_NOTIFICATIONS` requested **contextually**, with rationale and graceful denial handling; separate notification channels.
- [ ] Prominent disclosure + affirmative consent before first cloud upload of photos/records.

**Technical**
- [ ] Target **API 36** (Android 16).
- [ ] 🔴 **16 KB page-size compliance** — audit every native `.so` (SQLCipher, PDF libs, image codecs, Firebase NDK). Verify with `check_elf_alignment.sh`.
- [ ] **Play Billing Library 8.0.0+**.
- [ ] AAB + **Play App Signing**.
- [ ] Wear OS (if shipping): target API 34+, **64-bit support**, tiles/complications mentioned in the listing, Wear app quality guidelines met.

**Process**
- [ ] If stuck with a personal account: **12 testers, opted in continuously for 14 days, actually using the app**, before applying for production.
- [ ] App registered in Play Console (required for all distribution, per 15 Jul 2026 announcement).
- [ ] Title ≤ 30 chars; 2–8 phone screenshots; 4+ tablet screenshots if tablet-supported.

---

## 🍎 APPLE only

**App Store Connect**
- [ ] **Privacy Nutrition Labels** completed; pet health records declared as **User Content → Other User Content** (documented reasoning), not Health.
- [ ] **Privacy Policy URL** (required) + **User Privacy Choices URL** (optional — point at your deletion page).
- [ ] **Age rating questionnaire** answered, incl. the medical/wellness questions and (required from Sept 2026) the **social media questions**. Expect 13+. **Not** the Kids Category.
- [ ] **EU DSA trader status** verified.
- [ ] **License Agreement**: standard Apple EULA (then link it at the end of the app description) or upload a custom one.
- [ ] **App Review Information**: two demo accounts (one already in the other's household), pre-seeded data, a live invite link, a numbered walkthrough script, IAP/sandbox notes, "no location/contacts used."
- [ ] Enroll in the **Small Business Program** (15%).
- [ ] Enable **Family Sharing** on the subscription (⚠️ irreversible).
- [ ] Configure **Introductory Offer** for the free trial (one per subscription group).

**Code / build**
- [ ] Built with **Xcode 26 / iOS 26 SDK**; deliberate decision on Liquid Glass adoption.
- [ ] 🔴 **`PrivacyInfo.xcprivacy`** in the app target: `NSPrivacyCollectedDataTypes` matching the Nutrition Labels, `NSPrivacyAccessedAPITypes` with reason codes for **UserDefaults (CA92.1)**, **FileTimestamp**, **DiskSpace**, **SystemBootTime** as applicable to your KMP framework and SDKs.
- [ ] Verify the manifest survives the KMP → SPM/CocoaPods → app-target packaging; **generate the Privacy Report** and diff it against your labels.
- [ ] Audit every third-party SDK for a bundled privacy manifest **and signature**.
- [ ] **Sign in with Apple** implemented (mandatory because you offer Google Sign-In) — including token revocation on account deletion.
- [ ] **Specific, non-generic purpose strings**; use `PHPickerViewController` so you can omit `NSPhotoLibraryUsageDescription`; remove every unused usage-description key.
- [ ] **Restore Purchases** on the paywall.
- [ ] Rolling **64-notification** window for local notifications; server push for long-horizon reminders.
- [ ] No ads in widgets/extensions/watchOS (3.1.7); Live Activities strictly functional (4.5.3).
- [ ] 4.3(b) defence: household sharing, sitter access, interactive widgets, Watch quick-log, claim-pack PDF **all in v1** + a differentiation paragraph in review notes.

---

## ⚠️ Items flagged as uncertain — verify before relying on them

| # | Claim | Why uncertain | How to resolve |
|---|---|---|---|
| 1 | Medication-reminder apps don't qualify for `USE_EXACT_ALARM` | No official doc names them; inferred from "alarm clock / calendar" policy language | Submit a policy question via Play Console **before** building; worst case you ship `SCHEDULE_EXACT_ALARM` anyway (safe) |
| 2 | Play requires ≤ 2-tap in-app cancellation | Only in 2026 blog posts, not confirmed in Google docs | Build it regardless — zero cost |
| 3 | Health apps declaration deadline (Aug 2024 vs Jul 2026) | Google's help page and secondary sources disagree | Just complete it; it blocks publishing either way |
| 4 | Whether **pet** health data = "Health" under Apple labels / Play Data safety / Play Health declaration | No policy text addresses animals | Declare as User Content / Files & docs, document reasoning, ship the disclaimer as a hedge |
| 5 | Play AI policy's in-app reporting requirement | Consistent across summaries; not surfaced on the help page I fetched | Read the Play AI-Generated Content policy directly in Console before shipping any AI |
| 6 | 16 KB page-size deadline extension to 31 May 2026 | Community thread, not an official page | Irrelevant — both dates passed; just comply |
| 7 | iOS 64-pending-local-notification limit | Well-established in developer forums; no single crisp Apple doc | Architect around it regardless |
| 8 | EAA microenterprise exemption (<10 staff, ≤€2M) | National transpositions vary | **Get EU legal advice before EU launch** |
| 9 | Apple 4.3(b) applicability to pet-care apps | "Well-established categories" list is non-exhaustive | Differentiate aggressively; can't be resolved in advance |
| 10 | Apple/Play EU DMA fee percentages | Actively changing through late 2026 | Re-check at the Apple EU page if you revisit alternative billing |
| 11 | Play trader-status mechanics | Google's public documentation is thinner than Apple's | Check the current Play Console declaration form |

---

## Sources

**Apple — official**
- [App Review Guidelines](https://developer.apple.com/app-store/review/guidelines/)
- [Auto-renewable Subscriptions](https://developer.apple.com/app-store/subscriptions/)
- [App Privacy Details](https://developer.apple.com/app-store/app-privacy-details/)
- [App Store Small Business Program](https://developer.apple.com/app-store/small-business-program/)
- [Offering account deletion in your app](https://developer.apple.com/support/offering-account-deletion-in-your-app)
- [Privacy manifest files](https://developer.apple.com/documentation/bundleresources/privacy-manifest-files) · [Adding a privacy manifest](https://developer.apple.com/documentation/bundleresources/adding-a-privacy-manifest-to-your-app-or-third-party-sdk)
- [Upcoming SDK minimum requirements](https://developer.apple.com/news/?id=ueeok6yw)
- [Updated App Review Guidelines — 8 Jun 2026](https://developer.apple.com/news/?id=a233fmpw) · [— 6 Feb 2026](https://developer.apple.com/news/?id=d75yllv4)
- [Updated age ratings in App Store Connect](https://developer.apple.com/news/?id=ks775ehf) · [Social media questions in the age rating questionnaire](https://developer.apple.com/news/?id=tlur8uvi)
- [Apps without trader status removed from the EU App Store](https://developer.apple.com/news/?id=einwn76m) · [Manage EU DSA trader requirements](https://developer.apple.com/help/app-store-connect/manage-compliance-information/manage-european-union-digital-services-act-trader-requirements/)
- [Changes for apps in the European Union](https://developer.apple.com/support/apps-in-the-eu/) · [Updates for apps in the EU](https://developer.apple.com/news/?id=awedznci)
- [Enable Family Sharing for your subscriptions](https://developer.apple.com/news/?id=ksfkdwpr) · [Offer auto-renewable subscriptions](https://developer.apple.com/help/app-store-connect/manage-subscriptions/offer-auto-renewable-subscriptions/)
- [App Review](https://developer.apple.com/distribute/app-review/) · [Apple Developer Program membership](https://developer.apple.com/programs/whats-included/) · [Fee waivers](https://developer.apple.com/help/account/membership/fee-waivers/)
- [Account deletion requirement starts June 30](https://developer.apple.com/news/?id=12m75xbj) · [Copyright and trademark guidelines](https://www.apple.com/legal/intellectual-property/guidelinesfor3rdparties.html) · [If an app asks to track your activity](https://support.apple.com/en-us/102420)

**Google — official**
- [Play Payments policy](https://support.google.com/googleplay/android-developer/answer/9858738?hl=en) · [Subscriptions policy](https://support.google.com/googleplay/android-developer/answer/9900533?hl=en)
- [User Data policy](https://support.google.com/googleplay/android-developer/answer/10144311?hl=en) · [Data safety](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en) · [Account deletion requirements](https://support.google.com/googleplay/android-developer/answer/13327111?hl=en)
- [Permissions and APIs that Access Sensitive Information](https://support.google.com/googleplay/android-developer/answer/16558241?hl=en) · [Photo & Video Permissions compliance](https://support.google.com/googleplay/android-developer/answer/15800983?hl=en) · [Restricted permissions with minimum-scope alternatives](https://support.google.com/googleplay/android-developer/answer/14115180?hl=en) · [Prominent disclosure best practices](https://support.google.com/googleplay/android-developer/answer/11150561?hl=en)
- [User Generated Content policy](https://support.google.com/googleplay/android-developer/answer/9876937?hl=en)
- [Health apps declaration form](https://support.google.com/googleplay/android-developer/answer/14738291?hl=en) · [Health Content and Services](https://support.google.com/googleplay/android-developer/answer/16679511?hl=en) · [Health app categories](https://support.google.com/googleplay/android-developer/answer/13996367?hl=en)
- [AI-Generated Content policy](https://support.google.com/googleplay/android-developer/answer/14094294?hl=en) · [Declaring AI-generated content](https://support.google.com/googleplay/android-developer/answer/17262077?hl=en) · [Safeguarding AI-generated content](https://support.google.com/googleplay/android-developer/answer/16353813?hl=en)
- [Metadata policy](https://support.google.com/googleplay/android-developer/answer/9898842?hl=en) · [Store listing best practices](https://support.google.com/googleplay/android-developer/answer/13393723?hl=en) · [Prepare your app for review](https://support.google.com/googleplay/android-developer/answer/9859455?hl=en)
- [Target API level requirements](https://support.google.com/googleplay/android-developer/answer/11926878?hl=en) · [Play App Signing](https://support.google.com/googleplay/android-developer/answer/9842756?hl=en) · [Service fee](https://support.google.com/googleplay/android-developer/answer/11131145?hl=en) · [2021 service fee change](https://support.google.com/googleplay/android-developer/answer/10632485?hl=en)
- [Closed testing requirements for new personal accounts](https://support.google.com/googleplay/android-developer/answer/14151465?hl=en) · [Verify developer identity](https://support.google.com/googleplay/android-developer/answer/10841920?hl=en) · [Android developer verification](https://support.google.com/android-developer-console/answer/16561738?hl=en)
- [Target audience & content settings](https://support.google.com/googleplay/android-developer/answer/9867159?hl=en) · [Families Policies](https://support.google.com/googleplay/android-developer/answer/9893335?hl=en)
- Policy announcements: [Apr 15 2026](https://support.google.com/googleplay/android-developer/answer/16926792?hl=en) · [Jul 15 2026](https://support.google.com/googleplay/android-developer/answer/17134731) · [index](https://support.google.com/googleplay/android-developer/announcements/13412212?hl=en)
- [Schedule exact alarms denied by default](https://developer.android.com/about/versions/14/changes/schedule-exact-alarms) · [Schedule alarms](https://developer.android.com/develop/background-work/services/alarms) · [Doze & App Standby](https://developer.android.com/training/monitoring-device-state/doze-standby) · [Notification runtime permission](https://developer.android.com/develop/ui/compose/notifications/notification-permission) · [Opt-in notifications (AOSP)](https://source.android.com/docs/core/display/notification-perm)
- [Manage subscriptions (Play Billing)](https://developer.android.com/google/play/billing/manage-purchases) · [Migrate to Billing Library 8](https://developer.android.com/google/play/billing/migrate-gpblv8) · [Wear OS app quality](https://developer.android.com/develop/adaptive-apps/quality-guidelines/wear-app-quality)
- [Expanded billing choice and lower fees on Play](https://android-developers.googleblog.com/2026/06/play-expanded-billing.html) · [16 KB page size](https://android-developers.googleblog.com/2025/05/prepare-play-apps-for-devices-with-16kb-page-size.html) · [Android Photo Picker & user trust](https://android-developers.googleblog.com/2025/04/google-play-empowering-developers-to-build-user-trust-through-privacy.html)

**Legal / secondary (⚠️ non-official)**
- [Eighth Circuit vacates FTC Click-to-Cancel (Cooley)](https://www.cooley.com/news/insight/2025/2025-07-11-click-to-cancel-just-got-cancelled-eighth-circuit-vacates-entirety-of-ftcs-negative-option-rule) · [FTC moves to revive (Crowell)](https://www.crowell.com/en/insights/client-alerts/clicking-all-the-right-boxes-ftc-moves-to-revive-click-to-cancel-rule-following-eighth-circuit-vacatur)
- [California ARL amendments (Cooley)](https://www.cooley.com/news/insight/2025/2025-06-04-california-automatic-renewal-law-amendments-take-effect-on-july-1-2025) · [CA AG consumer alert](https://oag.ca.gov/news/press-releases/attorney-general-bonta-issues-consumer-alert-california%E2%80%99s-automatic-renewal-law)
- [EAA & EN 301 549 (Level Access)](https://www.levelaccess.com/blog/eu-accessibility-requirements-and-eaa-compliance/) · [EN 301 549 (Deque)](https://www.deque.com/en-301-549-compliance/)
- [Apple guideline changes June 2026 (MacRumors)](https://www.macrumors.com/2026/06/09/app-store-guidelines-low-quality-apps/) · [9to5Mac](https://9to5mac.com/2026/06/09/apple-tightens-app-review-guidelines-against-apps-that-do-not-add-value-to-the-app-store/)
- [Apple 15% fee guide (RevenueCat)](https://www.revenuecat.com/blog/engineering/small-business-program) · [Apple EU DMA update (RevenueCat)](https://www.revenuecat.com/blog/growth/apple-eu-dma-update-june-2025)

---

**Two closing recommendations, in priority order:**

1. **Incorporate and enroll as an Organization on both stores.** It removes the 12-tester/14-day blocker, satisfies Apple's preference for legal entities in sensitive-adjacent domains, makes the DSA trader declaration clean, and puts a liability shield between you and a health-adjacent app. This is the highest-leverage decision on the list and it has a ~2-week lead time (D-U-N-S), so start it now.

2. **Prototype and validate the Android exact-alarm path before you build anything else.** If exact alarms turn out to be unobtainable or unreliable in practice for your users, your medication-reminder value proposition needs to be re-architected (server push as primary, local alarm as backup) — and that is a foundational decision, not a polish item.