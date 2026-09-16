# ADR 0004 — Reminders are data; the OS holds only a cache

**Status:** Accepted, pending the Phase 0 Spike C result
**Date:** 2026-09-15

## Context

Job #1 for this product is *"don't let me screw up the medication schedule."* The highest-willingness-to-pay users are people with a cancer dog, a diabetic cat, a seizure dog, a post-surgery recovery.

Reminder failure is **the most widespread defect class in the entire category** — present in six of the apps surveyed:

- DogLog: notifications 10–60 minutes late
- Notepet: *"Alerts stop working or are late once you have more than one… so I missed pet meds because of this terrible app"*
- PetDesk: reminders that will not clear after the appointment
- PetNoter: recurring tasks deleting their own future occurrences
- dogcat.app: "every 3 hours" firing at 3am with no quiet hours

Two hard platform constraints make the naive approach impossible:

1. **iOS keeps only the 64 soonest-firing pending local notifications per app.** Silently. Anything beyond that is discarded. Multiple pets × multiple medications burns that budget instantly.
2. **Android 14+ denies `SCHEDULE_EXACT_ALARM` by default** for apps targeting API 33+. The auto-granted alternative, `USE_EXACT_ALARM`, is a Play-reviewed restricted permission limited to alarm-clock and calendar apps — **a pet medication app does not qualify**, and declaring it blocks the upload.

## Decision

**The schedule lives in the database. The OS holds a rolling, disposable cache of the next few occurrences.**

- `ScheduleRule` rows are the truth: pet, item, explicit clock times, day pattern, window, paused flag, critical flag.
- **Occurrences are derived, never stored as OS alarms.**
- A **materialiser** writes a rolling window of OS notifications — 7 days on Android, **≤ 40 slots on iOS** (staying well under the 64 cap) — and re-runs on: app foreground, background refresh, device reboot, timezone change, and **after any household member marks a dose done**.
- Android: `SCHEDULE_EXACT_ALARM` only. Check `canScheduleExactAlarms()` before every schedule, listen for the revoked broadcast, and ship a **working inexact fallback** with a visible in-app banner explaining reduced precision.
- iOS: `UNCalendarNotificationTrigger` for regular series (one trigger, does not consume the budget per occurrence); rolling window for irregular ones.
- **Push is never the primary medication reminder.** FCM/APNs carry "your partner logged a dose" and act as a backstop for long-horizon items like annual boosters.
- A **health probe** compares what should be queued against what the OS reports as queued, and tells the user when they differ. Port Motivoa's `ReminderHealthProbe`.

Never request `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` — it is a restricted permission whose acceptable use cases do not include "reminders might be delayed", and requesting it risks suspension.

## Consequences

**Good**

- Survives reboot, timezone change, and OS revocation of the permission, because the truth was never in the OS.
- One design covers both platforms' very different constraints.
- Cross-device dedup is natural: when one household member marks a dose done, re-materialisation cancels everyone else's notification.
- The user is told the truth when reminders are degraded, rather than silently missing doses — which is both honest and the difference between a support email and a 1★ review.

**Bad**

- More machinery than "schedule an alarm". The materialiser is real, testable code with real edge cases.
- Re-materialisation on foreground costs a little startup work.
- If a user denies exact alarms, medication reminders genuinely are less precise. We ship a disclaimer saying so — "delivery is not guaranteed; do not rely on this as the sole reminder for time-critical medication" — which is honest, and also liability protection.

## Revisit if

Spike C, or the Phase 1 beta, shows more than ~30% of Android users denying exact alarms. In that case the architecture flips to **server push as primary with local alarms as backup**, which is a foundational change and must not be discovered late.

## Related

- `ARCHITECTURE.md` — reminders section
- `PLAN.md` §6.7, §6.8, §10 Phase 0
- `COMPLIANCE.md` — the exact-alarm row
