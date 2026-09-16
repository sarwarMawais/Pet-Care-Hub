# Phase 0 — Foundations and spikes

**Weeks 0–3. None of this is feature code.**

Phase 0 exists to remove the three things that could invalidate the plan after months of work: the legal setup with a two-week lead time, the toolchain that cannot run on your machine, and the two architectural bets that only real devices can settle.

Do not start Phase 1 until every box here is ticked.

---

## 1. Legal and accounts — start on day one, it has the longest lead time

- [ ] Register a legal entity
- [ ] Apply for a **D-U-N-S number** — free, but allow 5–14 business days
- [ ] Enrol as an **Organization** (not Individual) on:
  - [ ] Apple Developer Program — $99/yr
  - [ ] Google Play Console — $25 one-time
- [ ] Complete developer identity verification on both
- [ ] Trademark search on the working name: US (USPTO), EU (EUIPO), UK (IPO), CA — see [`adr/0001`](adr/0001-provisional-name.md)
- [ ] Register the domain; set up a business email and a **business phone number** (both are publicly displayed under the EU DSA)
- [ ] Get a registered-office or virtual-office address — **never use a home address**

> **Why Organization matters:** it skips Play's 12-tester/14-day closed-test requirement entirely, satisfies Apple 5.1.1(ix)'s preference for legal entities in sensitive-adjacent domains, makes the DSA trader declaration clean, and puts a liability shield between you and a health-adjacent app. This is the highest-leverage decision in the whole plan.

## 2. Toolchain — the biggest practical blocker

**You cannot build, sign or upload an iOS app from Windows.** No exceptions.

- [ ] Decide: used Mac mini (M2/M4, ~€500–700) or rented Mac. Recommendation: **buy one.** CI covers *shipping* but not *diagnosing* — you will need interactive Xcode for crash symbolication, Instruments, VoiceOver testing and App Store screenshots
- [ ] Set up Codemagic (500 free min/month, then ~$0.10/min)
- [ ] Pin the Xcode version in CI — a mismatched SDK breaks framework linking
- [ ] **Produce one signed iOS build of a hello-world KMP app, end to end, and install it on a device.** Until this works, nothing else matters
- [ ] Generate the Gradle wrapper (`gradle wrapper --gradle-version 9.2`) and commit it
- [ ] Verify an Android debug build runs

## 3. The three spikes

Each one settles a decision that is expensive to reverse. Timebox each to 2–3 days.

### Spike A — Navigation 3 on iOS, with swipe-back

- [ ] Three screens, Navigation 3, running on a physical iPhone
- [ ] **Interactive edge-swipe-back works** — this is not free and must be wired explicitly
- [ ] `NavKey` polymorphic serializers hand-registered (reflection-based routes do not work off-JVM)
- [ ] Process death and restore

**Fail condition:** if swipe-back fights us for more than three days, switch to **Decompose**, which has the best iOS back-gesture and process-death story. Record the switch as an ADR.

### Spike B — Room KMP + PowerSync + Supabase EU round-trip

- [ ] Supabase project in an **EU region**; one table with RLS filtered by `household_id`
- [ ] Room KMP writing locally on both platforms (App Group container path on iOS)
- [ ] PowerSync syncing between them
- [ ] **Two physical devices**, one Android one iOS, same household
- [ ] Measure: how long from a write on device A to it appearing on device B, on Wi-Fi? **Target: under 3 seconds**
- [ ] Put device A in airplane mode, write three events, restore connectivity — do all three arrive, in order, with no duplicates?
- [ ] Revoke device B's membership server-side — does it stop receiving?

**Fail condition:** latency above ~3s or surprising conflict behaviour → fall back to Supabase Realtime plus a hand-written outbox. See [`adr/0002`](adr/0002-supabase-eu-plus-powersync.md).

### Spike C — Reminders on both platforms

- [ ] Android: `SCHEDULE_EXACT_ALARM` request flow on **Android 14, 15 and 16** (physical devices or emulators)
- [ ] Android: verify the **inexact fallback actually fires** when permission is denied
- [ ] Android: handle `ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED`
- [ ] Android: confirm the merged manifest contains `SCHEDULE_EXACT_ALARM` and **not** `USE_EXACT_ALARM`
- [ ] iOS: materialise a rolling window of ≤ 40 notifications and prove you stay under the 64 cap with 3 pets × 3 medications
- [ ] iOS: re-materialise on foreground and BGAppRefresh
- [ ] Both: reboot the device — do queued reminders survive?
- [ ] Both: change the timezone — do times stay correct?

**Fail condition:** if exact-alarm denial looks likely to exceed ~30% in the wild, the architecture flips to push-primary. See [`adr/0004`](adr/0004-reminders-are-data.md).

## 4. Policy questions — ask before building

- [ ] Submit a Play Console policy question: **does a pet-medication reminder app qualify for `USE_EXACT_ALARM`?** (Expected answer: no. Ship `SCHEDULE_EXACT_ALARM` regardless — it is always safe)
- [ ] Confirm how to answer the **Health apps declaration** for a veterinary/pet app, since the form's categories are human-oriented

## 5. Documents — draft now, publish before submission

- [ ] Privacy policy draft
- [ ] Terms of Use draft — 16+ minimum age, auto-renewal terms, veterinary disclaimer
- [ ] Community guidelines draft
- [ ] Decide on EU legal counsel for GDPR processor agreements and the EAA exemption question

---

## Definition of done

Phase 0 is complete when:

1. A signed iOS build reaches a physical device from CI
2. Two physical devices sync a household event in under 3 seconds, and survive airplane mode
3. A medication reminder fires on time on Android 14/15/16 **and** on iOS, survives a reboot, and degrades honestly when permission is denied
4. The Organization accounts exist on both stores
5. Every spike outcome is recorded — as a confirmation in the existing ADR, or as a new ADR if the fallback was taken

Then, and only then, start Phase 1.
